package com.bazra.usermanagement.security;

import io.swagger.models.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger Configuration
 * @author Bemnet
 * @version 4/2022
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.bazra.usermanagement")).paths(PathSelectors.any())
                .build();
    }

//        @Bean
//    public  Docket userApi(){
//        return  new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.restapi"))
//                .paths(regex("/rest.*"))
//                .build()
//                .apiInfo(metaInfo());
//    }
//
//    private metaInfo(){
//        ApiInfo apiInfo new ApiInfo(
//
//                "spring boot and swagger",
//                "spring boot api for youtube",
//                "1.0",
//                "Terms of Service",
//                new Contact("eman hashim", "https://youtube.....","emanhashimh@gmail.com"),
//                "Apache Licence Version 2.0",
//                "https://www.apache.org/licence.html"
//        );
//        return apiInfo;
//    }



}


