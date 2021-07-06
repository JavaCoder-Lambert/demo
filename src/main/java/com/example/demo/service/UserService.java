package com.example.demo.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
/**
 *  @Author: lzj
 *  @Date: 2021/3/21 22:14
 *  @Description:
 */ 

public interface UserService{
    /**
     * 根据用户名称获取用户对象
     * @param userName 用户名称
     * @return
     */
    User getUser(String userName);
}
