package com.gabe.mychat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan//拦截器扫描
@MapperScan("com.gabe.mychat.mapper")//项目中对应的mapper类的路径
public class MychatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MychatApplication.class, args);
    }

}
