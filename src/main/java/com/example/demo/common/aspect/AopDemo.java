package com.example.demo.common.aspect;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: lzj
 * @Date: 2021/9/17 22:52
 * @Description:
 */
public class AopDemo {
    static public void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        context.getBean(MathCalculator.class).div(5, 2);
    }
}
