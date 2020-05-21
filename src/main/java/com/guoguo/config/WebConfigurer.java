package com.guoguo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginVerifyInterceptor loginVerifyInterceptor;
    @Autowired
    private IdentityVerifyInterceptor identityVerifyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径
        List<String> list = new ArrayList<>();
        list.add("/fyl/**");
        // 非拦截路径
        List<String> excludePathList=new ArrayList<>();
        excludePathList.add("/fyl/user/ajaxLogin");
        registry.addInterceptor(loginVerifyInterceptor).excludePathPatterns(excludePathList).addPathPatterns(list);
        list = new ArrayList<>();
        list.add("/user/list/page");
        list.add("/user/insert");
        list.add("/user/update/*");
        list.add("/user/ajax/save");
        registry.addInterceptor(identityVerifyInterceptor).addPathPatterns(list);
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//    }
}