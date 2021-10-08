package com.example.demo.杂七杂八;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月08日 15:14
 */
public class Fanxing {
    static int countLegs(List<? extends Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1(List<Animal> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    /**
     * 下界通配符 < ? super E>
     * 下界: 用 super 进行声明，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至 Object
     * <p>
     * 在类型参数中使用 super 表示这个泛型中的参数必须是 E 或者 E 的父类。
     * 也就是说？的对象是T的父类 和extends相反
     *
     * @param dst
     * @param src
     * @param <T>
     */
    private static <T> void test(List<? super T> dst, List<T> src) {
        for (T t : src) {
            dst.add(t);
        }
    }


    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // dogs.add(new Dog("111",2121));
        dogs.add(Dog.builder().name("1211").countLegs(999).build());
        // 不会报错 上届：用 extends 关键字声明，表示参数化的类型可能是所指定的类型，或者是此类型的子类。
        countLegs(dogs);
        // 报错
        // countLegs1(dogs);
        List<Animal> animals = new ArrayList<>();
        test(animals, dogs);
        System.out.println(new Gson().toJson(animals));
        Dog dog1 = Dog.builder().name("999").countLegs(666).build();
        // 输出对象在内存中占用字节大小
        System.out.println("对象大小:"+ObjectSizeCalculator.getObjectSize(dog1));
        //查看对象的内存布局
        /**
         * OFFSET：偏移地址，单位为字节
         * SIZE：占用内存大小，单位为字节
         * TYPE：Class中定义的类型
         * DESCRIPTION：类型描述，Obejct header 表示对象头，alignment表示对齐填充
         * VALUE：对应内存中存储的值
         */
        //查看对象的内存布局
        System.out.println("内部信息"+ClassLayout.parseInstance(dog1).toPrintable());
        //查看对象外部信息，包括引用的对象
        System.out.println("外部信息"+GraphLayout.parseInstance(dog1).toPrintable());
        //查看对象内部总大小
        System.out.println("对象总大小"+GraphLayout.parseInstance(dog1).totalSize());
    }
}
