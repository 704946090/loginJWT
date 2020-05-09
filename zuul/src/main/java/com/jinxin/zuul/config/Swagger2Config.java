package com.jinxin.zuul.config;

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

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("说明文档")
                .description("接口说明文档")
                .termsOfServiceUrl("")
                .contact(new Contact("柳金鑫","704946090@qq.com","704946090@qq.com"))
                .version("1.0")
                .build();
    }
//    @Bean
//    public Docket buildDocket() {
//    return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(buildApiInf()) // .apiInfo(apiInfo())
//            .select()
//            .apis(RequestHandlerSelectors.basePackage(""))// 需要生成文档的包的位置
//            .paths(PathSelectors.any())
//            .build();
//}
//
//    private ApiInfo buildApiInf() {
//        return new ApiInfoBuilder()
//                .title("测试文档")
//                .description("Zuul+Swagger2构建RESTful APIs")
//                .termsOfServiceUrl("http://www.skyworth.com")
//                .contact(new Contact("skyworth", "http://www.skyworth.com", ""))
//                .version("1.0")
//                .build();
//    }

}
