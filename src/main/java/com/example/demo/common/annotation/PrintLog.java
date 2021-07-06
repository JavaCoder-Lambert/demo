package com.example.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 日志打印注解
 *
 * @Author: lzj
 * @Date: 2021/3/21 22:58
 * @Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PrintLog {
    //返回值 默认为空
    String value() default "";
    //是否输出打赢日志
    boolean isPrint() default false;
}
