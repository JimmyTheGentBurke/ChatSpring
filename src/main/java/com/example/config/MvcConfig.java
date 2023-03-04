package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/registration").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/chat").setViewName("chat");
        registry.addViewController("/login").setViewName("index");
        registry.addViewController("/message").setViewName("chat");
    }



}