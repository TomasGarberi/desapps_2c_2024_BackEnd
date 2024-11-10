package com.tpo.TPO;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Group 13 - DAI REST API Swagger - Lattice APP")
                        .version("1.0.0")
                        .description("<h3><b>This is a REST API Swagger for the TPO of DAI Subject<b></h3><br>\n" +
                                "       <img width=\"250\" align=\"left\" src=\"https://i.imgur.com/T1kfbAM.png\"/>\n"));
    }
}

