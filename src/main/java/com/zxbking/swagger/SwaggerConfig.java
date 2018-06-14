package com.zxbking.swagger;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Created by zhangxibin on 2016/8/9.
 */

@Configuration
@EnableSwagger2
@ConditionalOnProperty(value = "com.zxbking.swagger.import", havingValue = "true")
public class SwaggerConfig {
    @Value("${com.zxbking.swagger.title:APPNAME}")
    private String title;
    @Value("${com.zxbking.swagger.description:文档中可以查询及测试接口调用参数和结果}")
    private String description;
    @Value("${com.zxbking.swagger.version:V1.0}")
    private String version;
    @Bean
    public Docket app_api() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/rest.*")))
//                .paths(Predicates.not(PathSelectors.regex("/open.*")))
                .paths(PathSelectors.regex("/.*"))
                .build().groupName(title+"公开接口文档"+version)
                .apiInfo(apiInfo(title+"公开接口文档",description,version));
    }
    @Bean
    public Docket app_rest_api() {
        return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/rest/.*")).build().groupName(title+"鉴权接口文档"+version)
                .apiInfo(apiInfo(title+"鉴权接口文档",description,version));
    }


    private ApiInfo apiInfo(String name,String description,String version) {
        ApiInfo apiInfo = new ApiInfoBuilder().title(name).description(description).version(version).build();
        return apiInfo;
    }
}