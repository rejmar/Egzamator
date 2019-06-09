package com.mr.egzamator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket SwaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("egzamator-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Egzamator Swagger")
                .description("Egzamator Swagger")
                .contact(new Contact("Marcin Rejdych",
                        "localhost:8080/egzamator/api",
                        "maarcinrej@gmail.com"))
                .version("1.0.1")
                .build();
    }

//    private Predicate<String> getSwaggerPaths() {
//        return or(
//                regex("/api.*"),
//                regex("/test.*"));
//    }
}