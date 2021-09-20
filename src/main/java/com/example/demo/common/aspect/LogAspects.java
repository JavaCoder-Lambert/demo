package com.example.demo.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @Author: lzj
 * @Date: 2021/9/17 22:41
 * @Description:
 */
@Aspect
public class LogAspects {
    @Pointcut("execution(int com.example.demo.common.aspect.MathCalculator.div(int,int))")
    public void pointLzj() {
        System.out.println("进入切面");
    }
    @Before("com.example.demo.common.aspect.LogAspects.pointLzj()")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + " 除法运行,参数是：" + Arrays.asList(joinPoint.getArgs()));
    }
    @After("com.example.demo.common.aspect.LogAspects.pointLzj()")
    public void logAfter(){
        System.out.println("除法结束");
    }

//    @Around("com.example.demo.common.aspect.LogAspects.pointLzj()")
//    public Object logAround(ProceedingJoinPoint pjp){
//        return pjp.toString();
//    }

    @AfterReturning(value = "com.example.demo.common.aspect.LogAspects.pointLzj())", returning = "result")
    public void logReturn2(JoinPoint joinPoint, Object result) {
        System.out.println(joinPoint.getSignature().getName() + "除法返回" + result);
    }

    @AfterThrowing(value = "com.example.demo.common.aspect.LogAspects.pointLzj()", throwing = "exception")
    public void logException(Exception exception) {
        System.out.println("除法异常"+exception.toString());
    }


}
