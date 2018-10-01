package com.hjl.security.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {
    private String path = "C:\\Users\\huangjunlong\\Desktop";

    @PostMapping
    public Integer uoload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile = new File(path,"hello.txt");
        file.transferTo(localFile);
        return 1;
    }

    @GetMapping("/{id}")
    public void download(@PathVariable Integer id , HttpServletRequest request , HttpServletResponse response) throws Exception {
        try(InputStream inputStream = new FileInputStream(new File(path,"hello.txt"));
            OutputStream outputStream = response.getOutputStream()
        ){
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition" , "attachment;filename = hello.txt");
            IOUtils.copy(inputStream,outputStream);
        }
    }
}
