package com.udacity.dog.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@PropertySource(value = "classpath:swagger.properties")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("#{${swagger.api.info}}")
    private Map<String, String> apiInfo;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(apiInfo.get("title"))
                .description(apiInfo.get("description"))
                .version(apiInfo.get("version"))
                .license(apiInfo.get("license")).licenseUrl(apiInfo.get("licenseUrl")).build();
    }
}
