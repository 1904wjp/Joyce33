package com.moon.joyce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.moon.joyce.example.mapper")
@SpringBootApplication
public class JoyceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyceApplication.class, args);
    }

}
