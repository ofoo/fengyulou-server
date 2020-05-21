package com.guoguo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/fyl/user/login");
        registry.addInterceptor(loginVerifyInterceptor).excludePathPatterns(excludePathList).addPathPatterns(list);
        list = new ArrayList<>();
        list.add("/user/list/page");
        list.add("/user/insert");
        list.add("/user/update/*");
        list.add("/user/ajax/save");
        registry.addInterceptor(identityVerifyInterceptor).excludePathPatterns(excludePathList).addPathPatterns(list);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");
    }

    //    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("login");
//    }
    /*@Bean
    public FilterRegistrationBean registerFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.addUrlPatterns("/**");
        bean.setFilter(new CrosFilter());
        return bean;
    }*/
}