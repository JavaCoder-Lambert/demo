package com.example.demo.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.notify.INotifyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2021年11月19日 17:53
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/notify")
public class NotifyController {
    @Resource
    private INotifyService service;

    @PostMapping(value = "/receive")
    public String receive(@RequestBody String body) {
        //处理通知
        Integer status = service.handle(body);
        return "success";
    }
}
