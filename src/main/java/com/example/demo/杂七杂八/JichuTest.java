package com.example.demo.杂七杂八;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年06月20日 17:57
 */
public class JichuTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
