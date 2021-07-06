package com.example.demo.common.interceptor;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.example.demo.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lzj
 * @Date: 2021/3/21 22:29
 * @Description:
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    public Log log = LogFactory.getLog(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("Authorization");
        if (token != null) {
            boolean result = JwtUtils.verify(token);
            if (result) {
                return true;
            }
        }
        log.error("认证失败");
        response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
        return false;
    }
}
