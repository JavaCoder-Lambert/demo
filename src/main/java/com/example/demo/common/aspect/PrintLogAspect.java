package com.example.demo.common.aspect;

import com.example.demo.common.annotation.PrintLog;
import com.example.demo.utils.ApiLog;
import com.example.demo.utils.HttpContextUtils;
import com.example.demo.utils.IPUtils;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 注解实现类
 *
 * @Author: lzj
 * @Date: 2021/3/21 23:04
 * @Description:
 */
@Aspect
@Component
public class PrintLogAspect {
    /**
     * 定义切点，切点指向我们的自定义注解
     */
    @Pointcut("@annotation(com.example.demo.common.annotation.PrintLog)")
    public void getPointOut() {

    }

    /**
     * 使用环绕通知，获取方法名和写在注解中的参数
     * 环绕通知ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行，
     * 这也是环绕通知和前置、后置通知方法的一个最大区别。
     */
    @Around("getPointOut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        printSysLog(point, time, result);
        return result;
    }

    private void printSysLog(ProceedingJoinPoint joinPoint, long time, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        StringBuilder sb = new StringBuilder();
        PrintLog syslog = method.getAnnotation(PrintLog.class);
        boolean isPrint = false;
        if(syslog != null){
            //注解上的描述
            sb.append("operate:").append(syslog.value());
            isPrint = syslog.isPrint();
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sb.append(",").append(className).append(".").append(methodName).append("()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = new Gson().toJson(args);
            sb.append(",params:").append(params);
            //如果给的是true 则打印出执行的方法
            if (isPrint) {
                String resultJson = new Gson().toJson(result);
                sb.append(", result:").append(resultJson);
            }
        }catch (Exception e){
            System.out.println("啊啊啊啊");
        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sb.append(",ip:").append(IPUtils.getIpAddr(request));
        sb.append(",time:").append(time);
        ApiLog.info(sb.toString());
    }

}
