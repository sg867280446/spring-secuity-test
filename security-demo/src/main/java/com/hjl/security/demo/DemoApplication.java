package com.hjl.security.demo;

import com.hjl.security.demo.dto.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
/**
 * @RestController相当于是@Controller与@ResponseBody的结合
 */
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }


}
