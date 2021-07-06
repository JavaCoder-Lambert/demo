package com.example.demo.controller;

import com.example.demo.common.annotation.PrintLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: lzj
 * @Date: 2021/3/21 21:02
 * @Description:
 */
@Controller
public class HelloController {


    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/")
    @ResponseBody
    @PrintLog(value = "测试啦哈哈",isPrint = true)
    public String getHello() {
        return "hello1111";
    }


}
