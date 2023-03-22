package com.seChat.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/chat").setViewName("chat");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("chat");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/error").setViewName("http_cat_error");
    }

}