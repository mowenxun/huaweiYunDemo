package com.deepexi.dd.middle.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author cpb
 * @version 1.0
 * @date 2019/9/29 14:00
 */
@Configuration
public class ServletConfiguration extends WebMvcConfigurationSupport {
    @Bean
    public HeaderInterceptor headerInterceptor() {
        return new HeaderInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //如果是/api,属于controller的就剔除掉，因为controller交由切面去处理
        registry.addInterceptor(headerInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/**");
    }

    /**
     * 配置swagger-ui.html不拦截
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
