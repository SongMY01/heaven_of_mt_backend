package com.heavenofmt.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI heavenOfMtOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Heaven of MT API 명세서")
                        .description("엠티게임천국 백엔드 API 문서")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("프로젝트 깃허브")
                        .url("https://github.com/Heaven-of-MT/heaven_of_mt_backend"));
    }
}
