package com.example.demo.common.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author: lzj
 * @Date: 2021/9/17 22:51
 * @Description:   EnableAspectJAutoProxy //开启基于注解的AOP模式
 */
@Configuration
@EnableAspectJAutoProxy
public class MainConfig {
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
