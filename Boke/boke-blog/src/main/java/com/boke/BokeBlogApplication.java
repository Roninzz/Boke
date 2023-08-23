package com.boke;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boke.mapper")
public class BokeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BokeBlogApplication.class,args);
    }
}