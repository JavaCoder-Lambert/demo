package com.example.demo.杂七杂八;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月08日 15:14
 */
public class Fanxing {
    static int countLegs (List<? extends Animal > animals ) {
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static int countLegs1 (List< Animal > animals ){
        int retVal = 0;
        for ( Animal animal : animals )
        {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    /**
     * 下界通配符 < ? super E>
     * 下界: 用 super 进行声明，表示参数化的类型可能是所指定的类型，或者是此类型的父类型，直至 Object
     *
     * 在类型参数中使用 super 表示这个泛型中的参数必须是 E 或者 E 的父类。
     * 也就是说？的对象是T的父类 和extends相反
     *  @param dst
     * @param src
     * @param <T>
     */
    private static <T> void test(List<? super T> dst, List<T> src){
        for (T t : src) {
            dst.add(t);
        }
    }



    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
       // dogs.add(new Dog("111",2121));
        dogs.add(Dog.builder().name("1211").countLegs(999).build());
        // 不会报错 上届：用 extends 关键字声明，表示参数化的类型可能是所指定的类型，或者是此类型的子类。
        countLegs( dogs );
        // 报错
       // countLegs1(dogs);
        List<Animal> animals = new ArrayList<>();
        test(animals,dogs);
        System.out.println(new Gson().toJson(animals));
    }
}
