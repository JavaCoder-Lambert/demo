package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.BaseUserMapper;
import com.example.demo.model.BaseUser;
import com.example.demo.service.BaseUserService;
import org.springframework.stereotype.Service;

/**
 * @Author lizhijiang
 * @Version
 * @Description
 * @CreateTime 2022年08月08日 17:50
 */
@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
}
