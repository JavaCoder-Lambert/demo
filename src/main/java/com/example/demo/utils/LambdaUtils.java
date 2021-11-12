package com.example.demo.utils;

import com.example.demo.杂七杂八.Person;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年10月14日 14:44
 */
public class LambdaUtils {
    /**
     * 根据属性值去除重复值
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T , Object> keyExtractor) {
        Map<Object , Boolean> seen = new ConcurrentHashMap<>(8);
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
