package com.hjl.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.hjl.security.demo.validator.MyConstraint;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class User {
    public interface UserBaseView{}
    public interface UserDetailView extends UserBaseView{}

    private Integer id;

    @NotBlank
    private String name;

    /**
     * 自定义注解
     */
    //@MyConstraint(message = "密码不正确")
    /**
     * 自定义消息
     */
    @NotBlank(message = "密码不正确")
    private String password;

    private Date birthDay;

    @JsonView(UserBaseView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(UserBaseView.class)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonView(UserBaseView.class)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
