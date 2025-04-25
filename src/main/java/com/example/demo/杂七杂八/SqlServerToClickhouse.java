package com.example.demo.杂七杂八;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 表结构同步--sqlserver TO clickhouse
 * @Author: lzj
 * @Date: 2025/4/25 16:27
 * @Version: 1.0
 * @Description:
 */
public class SqlServerToClickhouse {
    // 10.0.1.71  SQL Server 连接信息
    private static final String URL = "jdbc:sqlserver://10.0.1.71:1433;databaseName=qsfdw";
    private static final String USER = "sa";
    private static final String PASSWORD = "qsf,.3@6#9";

    // 10.0.1.73  SQL Server 连接信息
//    private static final String URL = "jdbc:sqlserver://10.0.1.73:1433;databaseName=wisdomstore";
//    private static final String USER = "sa";
//    private static final String PASSWORD = "qsf@719,.ad";

    // 映射 SQL Server 数据类型到 ClickHouse 数据类型
    public static String mapDataType(String sqlType, int precision, int scale, int maxLength) {
        switch (sqlType.toLowerCase()) {
            case "int":
            case "integer":
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
            case "char":
            case "text":
                return "String";
            case "nvarchar":
            case "nchar":
            case "ntext":
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
                return "String"; // Time 存为字符串 "HH:MM:SS"
            default:
                return "String"; // 默认映射为 String
        }
    }

    // 获取表和列信息
    public static List<String> getTablesAndColumns(Connection conn) throws SQLException {
        List<String> ddlStatements = new ArrayList<>();
        String tableQuery = "SELECT schema_name(t.schema_id) AS schema_name, t.name AS table_name " +
                "FROM sys.tables t " +
                "WHERE t.type = 'U' AND t.is_ms_shipped = 0"; // 只选用户表

        try (Statement stmt = conn.createStatement();
             ResultSet tables = stmt.executeQuery(tableQuery)) {

            while (tables.next()) {
                String schema = tables.getString("schema_name");
                String tableName = tables.getString("table_name");
                String createTableSql = generateCreateTableStatement(conn, schema, tableName);
                ddlStatements.add(createTableSql);
            }
        }
        return ddlStatements;
    }

    // 生成 CREATE TABLE 语句
    public static String generateCreateTableStatement(Connection conn, String schema, String tableName) throws SQLException {
        StringBuilder ddl = new StringBuilder();
        ddl.append("CREATE TABLE ").append(schema).append(".").append(tableName).append(" (\n");

        // 获取列信息
        String columnQuery = "SELECT c.name AS column_name, t.name AS data_type, c.max_length, " +
                "c.precision, c.scale, c.is_nullable " +
                "FROM sys.columns c " +
                "JOIN sys.types t ON c.user_type_id = t.user_type_id " +
                "WHERE c.object_id = OBJECT_ID('" + schema + "." + tableName + "') " +
                "ORDER BY c.column_id";

        try (Statement stmt = conn.createStatement();
             ResultSet columns = stmt.executeQuery(columnQuery)) {

            List<String> columnsList = new ArrayList<>();
            while (columns.next()) {
                String columnName = columns.getString("column_name");
                String sqlType = columns.getString("data_type");
                int maxLength = columns.getInt("max_length");
                int precision = columns.getInt("precision");
                int scale = columns.getInt("scale");
                boolean isNullable = columns.getBoolean("is_nullable");

                String mappedType = mapDataType(sqlType, precision, scale, maxLength);
                if (isNullable) {
                    mappedType = "Nullable(" + mappedType + ")";
                }

                columnsList.add("    " + columnName + " " + mappedType);
            }

            // 添加列定义
            ddl.append(String.join(",\n", columnsList));
        }

        // 使用主键作为 ORDER BY（如果有）
        String primaryKeyQuery = "SELECT c.name AS column_name " +
                "FROM sys.indexes i " +
                "JOIN sys.index_columns ic ON i.object_id = ic.object_id AND i.index_id = ic.index_id " +
                "JOIN sys.columns c ON c.object_id = i.object_id AND c.column_id = ic.column_id " +
                "WHERE i.is_primary_key = 1 AND i.object_id = OBJECT_ID('" + schema + "." + tableName + "')";

        try (Statement stmt = conn.createStatement();
             ResultSet primaryKey = stmt.executeQuery(primaryKeyQuery)) {

            List<String> primaryKeyColumns = new ArrayList<>();
            while (primaryKey.next()) {
                primaryKeyColumns.add(primaryKey.getString("column_name"));
            }

            if (!primaryKeyColumns.isEmpty()) {
                ddl.append("\n) ENGINE = MergeTree() ORDER BY (")
                        .append(String.join(", ", primaryKeyColumns))
                        .append(");");
            } else {
                ddl.append("\n) ENGINE = MergeTree() ORDER BY (")
                        .append("`" + schema + "." + tableName + "_id`")
                        .append(");"); // 如果没有主键，默认使用第一列
            }
        }

        return ddl.toString();
    }

    // 主方法
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // 获取所有表的 DDL 语句
            List<String> ddlStatements = getTablesAndColumns(conn);

            // 将 DDL 语句写入文件
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("clickhouse_schema.sql"))) {
                for (String ddl : ddlStatements) {
                    writer.write(ddl + "\n\n");
                }
            }

            System.out.println("Generated ClickHouse DDL statements in 'clickhouse_schema.sql'.");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
