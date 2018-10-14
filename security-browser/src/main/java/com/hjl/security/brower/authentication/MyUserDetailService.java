package com.hjl.security.brower.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MyUserDetailService implements UserDetailsService{
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登录用户名 : "+username);
        String password = getPasswordByUsername(username);

        return new User(username,password,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


    /**
     * 模拟数据库获取密码
     * @return
     */
    private String getPasswordByUsername(String username){
        //123456的加密编码
        return "$2a$10$mcM3zPqtrsaoHiCTRF5RYOcFUZZdDjPeKTvG9UgY48PYSOhUiREPm";
    }
}
