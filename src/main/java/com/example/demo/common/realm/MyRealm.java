package com.example.demo.common.realm;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * @Author: lzj
 * @Date: 2021/3/21 22:06
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //拿到封装好账户密码的token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        //用户校验
        User user = this.userService.getUser(userName);
        if (Objects.isNull(user)) {
            throw new AuthenticationException("用户名或密码错误！");
        }
        //加盐 计算盐值 保证每个加密后的 MD5 不一样
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
                this.getName());
    }
}
