package com.example.demo.杂七杂八;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月03日 14:33
 */
public class Demo {
    public static boolean isNumeric(final String str) {
        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }

    private static List<String> getNearLocationCodes(String locationCode){
        if(StringUtils.isBlank(locationCode)){
            return Collections.emptyList();
        }
        List<String> values=new ArrayList<>(2);
        int num =locationCode.length();
        String valueBefore=locationCode.substring(0,num-1);
        String valueAfter=locationCode.substring(num-1,num);
        if(isNumeric(valueAfter)){
            int number=Integer.parseInt(valueAfter);
            if(number-1>0){
                values.add(valueBefore+(number-1));
            }
            values.add(valueBefore+(number+1));
        }
        return values;
    }

    public static void main(String[] args) {
//        String locationCode="1_1_1_2";
//        System.out.println(new Gson().toJson(getNearLocationCodes(locationCode)));
        List<String> strings=new ArrayList<>();
        strings.add("quality_06");
        strings.add("quality_05");
        System.out.println(new Gson().toJson(strings));
        String value="[\"quality_06\",\"quality_05\"]";
        List<String> values= JSONObject.parseArray(value,String.class);
        System.out.println(values);
    }
}
