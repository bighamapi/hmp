package org.bighamapi.hmp.config;

import org.bighamapi.hmp.interceptor.AdminInterceptor;
import org.bighamapi.hmp.interceptor.HmpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    public HmpInterceptor hmpInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 可添加多个，这里选择拦截所有请求地址，进入后判断是否有加注解即可
        registry.addInterceptor(hmpInterceptor).addPathPatterns("/**");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/**");
    }
}