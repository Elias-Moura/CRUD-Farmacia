package io.farmacia.Generation.infra.docs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI springFarmacia() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD Farmacia")
                        .description("Projeto CRUD Farmacia - Generation Brasil")
                        .version("v0.0.1")
                        .contact(new Contact().name("Elias Moura")
                                .url("https://github.com/Elias-Moura")
                                .email("eliasmoura.py@gmail.com"))

                ).externalDocs(new ExternalDocumentation()
                        .description("Github")
                        .url("https://github.com/Elias-Moura/CRUD-Farmacia")
                );
    }
}
