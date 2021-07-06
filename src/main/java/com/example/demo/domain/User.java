package com.example.demo.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @Author: lzj
 *  @Date: 2021/3/21 22:14
 *  @Description:
 */ 
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
    * 用户ID
    */
    private Integer userId;

    /**
    * 用户名称
    */
    private String userName;

    /**
    * 密码
    */
    private String password;

    private static final long serialVersionUID = 1L;
}