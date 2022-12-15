package com.moneim.api.swaager.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String DEFAULT_INCLUDE_PATTERN = "/v1/.*";
//
//    @Bean
//    public Docket customDocket() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .paths(regex(DEFAULT_INCLUDE_PATTERN))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build()
//                .apiInfo(apiInfo())
//                .securitySchemes(Lists.newArrayList(apiKey()));
//
//    }
//
//    ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Journalisation APP")
//                .description("Api Journalisation")
//                .license("@CopyRight 2022")
//                .licenseUrl("licenseUrl")
//                .termsOfServiceUrl("")
//                .version("V0.1")
//                .contact(new Contact("Hammadi Moneim",
//                        "localhost:90909/swagger-ui.html#/",
//                        "moneimhamadi@yahoo.com"))
//                .build();
//    }
//
//
//    private ApiKey apiKey() {
//        return new ApiKey("Bearer",
//                AUTHORIZATION_HEADER,
//                "header");
//    }
//

}