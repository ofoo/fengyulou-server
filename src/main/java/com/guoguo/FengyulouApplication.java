package com.guoguo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.guoguo.fengyulou.dao")
public class FengyulouApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FengyulouApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FengyulouApplication.class);
    }

}
