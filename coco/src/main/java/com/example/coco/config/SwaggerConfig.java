package com.example.coco.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI cocoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("COCO API")
                        .description("홈 화면, 마이페이지, 리뷰 및 미션 관련 API 명세서")
                        .version("v1.0.0"));
    }
}