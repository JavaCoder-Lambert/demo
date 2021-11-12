package com.example.demo.杂七杂八;

import com.example.demo.utils.LambdaUtils;
import com.google.gson.Gson;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: lzj
 * @Date: 2021/9/22 22:01
 * @Description:
 */
public class LambdaDemo {
    public static void main(String[] args) {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            private static final long serialVersionUID = 6535629416990372831L;

            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 2000, 18));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 2371, 55));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 3322, 25));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 35020, 15));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 2272, 25));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 2057, 87));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 3120, 99));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 345, 25));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 3375, 14));
                add(new Person("Addison", "Pam", "Java programmer", "female", 3426, 20));
            }
        };
        ////返回数值流，减少拆箱封箱操作，避免占用内存  IntStream
        int asInt = javaProgrammers.stream()
                .mapToInt(Person::getSalary)
                .reduce((x, y) -> x += y)
                .getAsInt(); //return int
        int sumSalary=javaProgrammers.stream().mapToInt(Person::getSalary).sum();
        System.out.println("方式一   reduce(BinaryOperator<T> accumulator)   求薪资测试结果："+asInt);
        System.out.println("方式一   sum   求薪资测试结果："+sumSalary);

        /**
         * 注意：
         *      1.与方式一相比设置了累加器的初始值，参数一（x）则不再是Stream中的第一个数据而是设置的初始值（10000）其他相同
         */
        int reduce = javaProgrammers.stream().mapToInt(Person::getSalary).reduce(10000, (x, y) -> x += y);
        System.out.println("方式二  reduce(T identity, BinaryOperator<T> accumulator)   求薪资测试结果："+reduce);

        List<Person> testValues=javaProgrammers.stream().filter(LambdaUtils.distinctByKey(Person::getAge)).collect(Collectors.toList());
        System.out.println(new Gson().toJson(testValues));
        /**
         * reduce有三个重载方法
         * 1.一个参数的reduce
         * Optional<T> reduce(BinaryOperator<T> accumulator);
         * 参数： BinaryOperator<T> accumulator , BinaryOperator 继承于 BiFunction, 这里实现 BiFunction.apply(param1,param2) 接口即可。支持lambda表达式，形如：(result,item)->{...} 。
         *
         * 返回值：返回Optional对象，由于结果存在空指针的情况（当集合为空时）因此需要使用Optional。
         * ————————————————
         * 版权声明：本文为CSDN博主「薛定谔的雄猫」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
         * 原文链接：https://blog.csdn.net/iteye_19045/article/details/106672718
         */
        List<Integer> list= Stream.of(1,2,3,4,5).collect(Collectors.toList());
        List<Integer> list22=new ArrayList<>(list);
        list22.set(0,11);
        System.out.println("初始前："+list);
        System.out.println("初始后"+list22);
        //将数组进行累加求和
        //由于返回的是 Optional ，因此需要get()取出值。
        Integer total=list.stream().reduce((result,item)->result+item).get();
        System.out.println(total);
//        /**
//         * 将累加的每一步打印，可以发现Lambda表达式中的两个参数(result,item)的含义：
//         * 第一个参数 result ：初始值为集合中的第一个元素，后面为每次的累加计算结果 ；
//         * 第二个参数 item ：遍历的集合中的每一个元素（从第二个元素开始，第一个被result使用了）。
//         * /* 结果如下：
//         * result=1, item=2
//         * result=3, item=3
//         * result=6, item=4
//         * result=10, item=5
//         *
//         */
//        list.stream().reduce((result,item)->{
//            System.out.println("result="+result+", item="+item);
//            return result+item;
//        });
//
//        /**
//         * 2.两个参数的reduce
//         * T reduce(T identity, BinaryOperator<T> accumulator);
//         * 参数1：T identity 为一个初始值（默认值） ，当集合为空时，就返回这个默认值，当集合不为空时，该值也会参与计算；
//         * 参数2：BinaryOperator<T> accumulator 这个与一个参数的reduce相同。
//         * 返回值：并非 Optional，由于有默认值 identity ，因此计算结果不存在空指针的情况。
//         * ————————————————
//         * 版权声明：本文为CSDN博主「薛定谔的雄猫」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//         * 原文链接：https://blog.csdn.net/iteye_19045/article/details/106672718
//         */
//        Integer total1=list.stream().reduce(0,(result,item)->result+item);
//        System.out.println(total1);//结果为：15
//
//        list=new ArrayList<>();
//        total=list.stream().reduce(0,(result,item)->result+item);
//        System.out.println(total);//数组为空时，结果返回默认值0


    }


}
