package com.example.PasswordSaver.config;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;


@Configuration
@EnableSwagger2
public class SwaggerConfig 
{

	@Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("PasswordSaver")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/.*"))
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Password Saver")
                .termsOfServiceUrl("https://github.com/hurutta")
                .version("1.0")
                .contact(new springfox.documentation.service.Contact("Abid Mohammad Jawad","https://github.com/hurutta", "abidmohammadjawad@gmail.com"))
                .build();
    }

}