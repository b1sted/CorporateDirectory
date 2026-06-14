package ru.basted.corporatedirectory.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI defineOpenApi() {
        Contact myContact = new Contact();
        myContact.setName("Nikita Mandrykin");
        myContact.setEmail("admin@basted.ru");

        Info information = new Info()
                .title("Corporate Directory API")
                .version("1.0")
                .description("Этот API предоставляет конечные точки для управления сотрудниками.")
                .contact(myContact);

        return new OpenAPI().info(information);
    }
}
