package com.hjl.security.brower;

import com.hjl.security.brower.authentication.MyAuthenticationFailureHandler;
import com.hjl.security.brower.authentication.MyAuthenticationSuccessHandler;
import com.hjl.security.brower.logout.LogoutHandler;
import com.hjl.security.brower.session.MyExpiredSessionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyAuthenticationSuccessHandler successHandler;

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;


    protected void configure(HttpSecurity http) throws Exception{
        http.formLogin()
//        http.httpBasic()
            .loginPage("/authentication/require")
                .loginProcessingUrl("/sublogin")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutHandler())
                .deleteCookies("SESSION")
            .and()
            .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(new MyExpiredSessionStrategy())
            .and()
            .and()
            .authorizeRequests()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/authentication/require").permitAll()
                .antMatchers("/session/invalid").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest()
                .authenticated()
            .and()
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
