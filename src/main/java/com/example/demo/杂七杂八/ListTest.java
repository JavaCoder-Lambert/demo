package com.example.demo.杂七杂八;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: lzj
 * @Date: 2022/6/6 23:39
 * @Description:
 */
public class ListTest {


    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            private static final long serialVersionUID = 6535629416990372831L;
            {
                List<String> test1=new ArrayList<>(Arrays.asList("22"));
                List<String> test2=new ArrayList<>(Arrays.asList("33"));
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18,test1));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55,test2));
             }
        };
        List<String> list=new ArrayList<>(Arrays.asList("1","22","33"));
        javaProgrammers.forEach(item->{
            
        });
    }
}
