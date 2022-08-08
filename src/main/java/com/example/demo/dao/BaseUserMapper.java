package com.example.demo.dao;

import com.example.demo.model.BaseUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员 Mapper 接口
 * </p>
 *
 * @author lizhijiang
 * @since 2021-10-11
 */
@Repository
public interface BaseUserMapper extends BaseMapper<BaseUser> {

}
