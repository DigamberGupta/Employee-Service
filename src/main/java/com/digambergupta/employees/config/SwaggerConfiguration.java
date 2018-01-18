package com.digambergupta.employees.config;


import org.springframework.beans.factory.annotation.Value;
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
 * Configuration class which allowing to define Swagger API for the project
 *
 * @author Digamber
 */
@SuppressWarnings({"unused"})
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String DEFAULT_REST_PACKAGE = "com.digambergupta.employees.controller";

    @Value("${spring.application.name}")
    private String applicationName;


    @Bean
    public Docket unsecuredAuthApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(this.applicationName)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                //following line will expose only internal/service APIs
                //if Actuator endpoints have to be exposed as well - comment it out
                .apis(RequestHandlerSelectors.basePackage(DEFAULT_REST_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Employee Service REST API")
                .description("RESTful API to manage Employee Details")
                .contact(new Contact("Digamber Gupta", "www.digambergupta.com", "digambergupta1992@gmail.com"))
                .version("1.0")
                .build();
    }
}
