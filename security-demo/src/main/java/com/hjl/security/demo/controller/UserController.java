package com.hjl.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hjl.security.demo.dto.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(User.UserBaseView.class)
    /**
     * 1、这里的参数(username)可以加@RequestParam，就样就可以改变参数的名称，以及可以设置
     * 是否强制参数不为null，还可以设置参数的默认值。
     * 2、如果有多个参数，也可以封装成一个dto
     * 3、参数的默认接收形式是Key-Value的形式
     */
    @ApiOperation(value = "用户列表")
    public List<User> getUsers(@ApiParam(value = "用户名") String username){
        System.out.println(username);
        List<User> users = new ArrayList<>();
        users.add(new User());
        return users;
    }

    /**
     * \\d+  正则表达式，表示整数
     * @param id
     * @return
     */
    @GetMapping("{id:\\d+}")
    /**
     * @GetMapping("{id}") 等同于 @RequestMapping(value = "{id}" , method = RequestMethod.GET)
     * 类似的还有@PostMapping等
     */
    @JsonView(User.UserDetailView.class)
    public User getUserInfo(@PathVariable String id){
        System.out.println(id);
        User user = new User();
        user.setName("aFightz");
        return user;
    }


    @PostMapping
    @JsonView(User.UserBaseView.class)
    /**
     * 在@Valid下，如果没有BindingResult，并且User的参数校验不通过，就会返回400
     */
    public User CreateUser(@Valid @RequestBody User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            for(ObjectError error : bindingResult.getAllErrors()){
                /**
                 * 获得校验不通过的字段
                 */
//                FieldError fieldError = (FieldError)error;
//                System.out.println(fieldError.getField());


                System.out.println(error.getDefaultMessage());
                /**
                 * 如果真的检测到有错误，那么该return什么？返回自定义的json错误信息吗？
                 */
            }
        }
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthDay());
        user.setId(1);
        return user;
    }


    @GetMapping("/exception")
    public void testException(){
        throw new RuntimeException();
    }
}
