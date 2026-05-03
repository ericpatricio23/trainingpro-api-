package com.eric.apitraining.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TrainingPro AI")
                        .description("API REST para geração de treinos personalizados com Inteligência Artificial para qualquer modalidade esportiva.")
                        .version("1.0.0")
                );
    }
}