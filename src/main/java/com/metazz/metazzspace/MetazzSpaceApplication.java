package com.metazz.metazzspace;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.metazz.metazzspace.mapper")
public class MetazzSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetazzSpaceApplication.class, args);
    }

}
