package com.example.demo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会员
 * </p>
 *
 * @author lizhijiang
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("base_user")
public class BaseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private String gender;

    /**
     * 姓名
     */
    private String realName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 学号
     */
    private String userNo;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 学校
     */
    private String schoolName;

    private Integer integral;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
