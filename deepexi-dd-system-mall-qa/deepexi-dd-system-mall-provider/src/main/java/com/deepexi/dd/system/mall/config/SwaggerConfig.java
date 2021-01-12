package com.deepexi.dd.system.mall.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {


    @Value("${swagger.host}")
    private String host;
    @Value("${system.mall.swagger.basePackages:com.deepexi.dd.system.mall.controller}")
    String swaggerBasePackages;

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("Authorization").description("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        List<Parameter> aParameters = Lists.newArrayList();
        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2).host(host).apiInfo(apiInfo())
                .globalOperationParameters(aParameters)
                .select()
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerBasePackages)).paths(PathSelectors.any()).build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring cloud 脚手架使用 Swagger2 构建RESTful API")
                //创建人
                .contact(new Contact("deepexi", "http://www.deepexi.com", "hudong@deepexi.com"))
                //版本号
                .version("1.0")
                //描述
                .description("api管理").build();
    }

}