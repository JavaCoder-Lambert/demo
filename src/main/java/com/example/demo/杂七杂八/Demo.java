package com.example.demo.杂七杂八;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.enums.DocTallyOverTypeEnum;
import com.google.gson.Gson;

import java.sql.SQLOutput;
import java.util.*;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月03日 14:33
 */
public class Demo {
    public static long MAX_SHEDULE_PEROID = 3600* 1000 * 24 * 45L;

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
//        List<String> strings=new ArrayList<>();
//        strings.add("quality_06");
//        strings.add("quality_05");
//        Iterator<String> test=strings.iterator();
//        while(test.hasNext()){
//            String key=test.next();
//            if(key.equalsIgnoreCase("quality_05")){
//                test.remove();
//                //需要添加continue 进行下一个元素循环
//                continue;
//             }
//            System.out.println("找到了并且删除了,当前值是"+key);
//        }
//        System.out.println(new Gson().toJson(strings));
        //
//        String value="['肖骏峰','龙孟媛']";
//        List<String> values= JSONObject.parseArray(value,String.class);
//        System.out.println(values);
//        //'[337,1399283584782569473]'
//        String longtest="[337,1399283584782569473]";
//        List<Long> longtest1= JSONObject.parseArray(longtest,Long.class);
//        System.out.println(longtest1);
//        String value=DocTallyOverTypeEnum.getEnumValueByCode(1);
//
//        System.out.println(value);
        List<String> list=Arrays.asList("a","li");
        System.out.println(new Gson().toJson(list));

        System.out.println(justForFun("li","asdadsa"));
        System.out.println(MAX_SHEDULE_PEROID);
    }

//    /**
//     * 每隔49S进行数据处理
//     */
//    public static class TenNumber
//    {
//        private static int index = 0;
//        public static void main(String[] args)
//        {
//            Timer timer = new Timer();
//            timer.schedule(new TimerTask()
//            {
//                @Override
//                public void run()
//                {
//                    System.out.println("49S输出信息");
//                }
//            }, 0, 1000);
//        }
//    }

    private static String justForFun(String... test){
        return JSONObject.toJSONString(test);
    }


}
