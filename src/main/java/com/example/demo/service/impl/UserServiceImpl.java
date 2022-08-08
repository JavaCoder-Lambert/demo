package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.model.BaseUser;
import com.example.demo.service.BaseUserService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: lzj
 * @Date: 2021/3/21 22:16
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private BaseUserService baseUserService;

    @Override
    public User getUser(String userName) {
        return null;
    }

    @Override
    public BaseUser getOneUserById(Long id) {
        return baseUserService.getById(1);
    }
}
