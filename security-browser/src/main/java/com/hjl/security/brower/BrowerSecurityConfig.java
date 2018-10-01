package com.hjl.security.brower;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{
    protected void configure(HttpSecurity http) throws Exception{
//        http.formLogin()
        http.httpBasic()
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated();
    }
}
