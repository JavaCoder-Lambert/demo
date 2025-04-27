package com.example.demo.杂七杂八;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 表结构同步--sqlserver TO clickhouse
 * @Author: lzj
 * @Date: 2025/4/25 16:27
 * @Version: 1.0
 * @Description:
 */
public class SqlServerToClickhouse {
    // 10.0.1.71  SQL Server 连接信息
//    private static final String URL = "jdbc:sqlserver://10.0.1.71:1433;databaseName=qsfdw";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "qsf,.3@6#9";

    // 10.0.1.73  SQL Server 连接信息
    private static final String URL = "jdbc:sqlserver://10.0.1.73:1433;databaseName=wisdomstore";
    private static final String USER = "sa";
    private static final String PASSWORD = "qsf@719,.ad";

    // 表名正则匹配，不处理以test或tmp开头的表
    private static final Pattern TABLE_NAME_PATTERN = Pattern.compile("^(?!test|tmp).*", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            List<String> ddlStatements = getTablesAndColumns(conn);

            // 写入本地文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("clickhouse_schema.sql"))) {
                for (String ddl : ddlStatements) {
                    writer.write(ddl + "\n\n");
                }
            }

            System.out.println("DDL statements written to clickhouse_schema.sql successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 读取表和生成DDL
    private static List<String> getTablesAndColumns(Connection conn) throws SQLException {
        List<String> ddlList = new ArrayList<>();
        String tableQuery = "SELECT schema_name(t.schema_id) AS schema_name, t.name AS table_name " +
                "FROM sys.tables t " +
                "WHERE t.type = 'U' AND t.is_ms_shipped = 0";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(tableQuery)) {

            while (rs.next()) {
                String schema = rs.getString("schema_name");
                String tableName = rs.getString("table_name");

                // 正则匹配表名
                if (!TABLE_NAME_PATTERN.matcher(tableName).matches()) {
                    continue; // 不匹配，跳过
                }

                String ddl = generateCreateTableStatement(conn, schema, tableName);
                ddlList.add(ddl);
                System.out.println("Generated DDL for table: " + tableName);
            }
        }
        return ddlList;
    }

    // 生成ClickHouse建表语句
    private static String generateCreateTableStatement(Connection conn, String schema, String tableName) throws SQLException {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(escapeName(tableName)).append(" (\n");

        String columnQuery = "SELECT c.name AS column_name, t.name AS data_type, c.max_length, " +
                "c.precision, c.scale, c.is_nullable " +
                "FROM sys.columns c " +
                "JOIN sys.types t ON c.user_type_id = t.user_type_id " +
                "WHERE c.object_id = OBJECT_ID(?) " +
                "ORDER BY c.column_id";

        List<String> columnDefs = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(columnQuery)) {
            pstmt.setString(1, schema + "." + tableName);
            ResultSet columns = pstmt.executeQuery();
            while (columns.next()) {
                String colName = columns.getString("column_name");
                String sqlType = columns.getString("data_type");
                int maxLength = columns.getInt("max_length");
                int precision = columns.getInt("precision");
                int scale = columns.getInt("scale");
                boolean isNullable = columns.getBoolean("is_nullable");

                String mappedType = mapDataType(sqlType, precision, scale, maxLength);
                if (isNullable) {
                    mappedType = "Nullable(" + mappedType + ")";
                }
                columnDefs.add("    " + escapeName(colName) + " " + mappedType);
            }
        }

        ddl.append(String.join(",\n", columnDefs)).append("\n)");

        // 主键处理
        List<String> primaryKeys = getPrimaryKeys(conn, schema, tableName);
        if (!primaryKeys.isEmpty()) {
            ddl.append(" ENGINE = MergeTree() ORDER BY (").append(String.join(", ", primaryKeys)).append(");");
        } else {
            ddl.append(" ENGINE = MergeTree() ORDER BY tuple();"); // 如果没有主键，默认 tuple()
        }

        return ddl.toString();
    }

    // 查询表主键列
    private static List<String> getPrimaryKeys(Connection conn, String schema, String tableName) throws SQLException {
        List<String> pkList = new ArrayList<>();
        String pkQuery = "SELECT c.name AS column_name " +
                "FROM sys.indexes i " +
                "JOIN sys.index_columns ic ON i.object_id = ic.object_id AND i.index_id = ic.index_id " +
                "JOIN sys.columns c ON c.object_id = ic.object_id AND c.column_id = ic.column_id " +
                "WHERE i.is_primary_key = 1 AND i.object_id = OBJECT_ID(?)";

        try (PreparedStatement pstmt = conn.prepareStatement(pkQuery)) {
            pstmt.setString(1, schema + "." + tableName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pkList.add(escapeName(rs.getString("column_name")));
            }
        }
        return pkList;
    }

    // 数据类型映射
    private static String mapDataType(String sqlType, int precision, int scale, int maxLength) {
        switch (sqlType.toLowerCase()) {
            case "int":
                return "Int32";
            case "bigint":
                return "Int64";
            case "smallint":
                return "Int16";
            case "tinyint":
                return "UInt8";
            case "bit":
                return "UInt8";
            case "decimal":
            case "numeric":
                return "Decimal(" + precision + "," + scale + ")";
            case "float":
                return "Float64";
            case "real":
                return "Float32";
            case "varchar":
            case "nvarchar":
            case "char":
            case "nchar":
            case "text":
            case "ntext":
            case "varbinary":
            case "binary":
            case "image":
                return "String";
            case "uniqueidentifier":
                return "UUID";
            case "datetime":
            case "smalldatetime":
            case "datetime2":
            case "datetimeoffset":
                return "DateTime";
            case "date":
                return "Date";
            case "time":
                return "String";
            default:
                return "String";
        }
    }

    // ClickHouse要求字段、表名用反引号包裹
    private static String escapeName(String name) {
        return "`" + name + "`";
    }
}
