package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ConvertUtil {

    public static <T> T convert(Object source, Class<T> clazz){

        if(source == null){
            return null;
        }

        T t = null;

        try {
            t = clazz.newInstance();
        } catch (Exception e) {
            log.error("convert exception", e);
        }

        if(t != null){
            BeanUtils.copyProperties(source, t);
        }

        return t;
    }

    public static <A, B> List<B> convertList(List<A> source, Class<B> clazz){

        List<B> list = new ArrayList<>();

        if(CollectionUtils.isEmpty(source)){
            return list;
        }

        for (A a : source) {
            list.add(convert(a, clazz));
        }

        return list;
    }

    public static void convert(Object source, Object target){
        BeanUtils.copyProperties(source, target);
    }

}
