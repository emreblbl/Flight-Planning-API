package com.example.flight.base.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Configuration
public class SwaggerConfig {

public static final Contact CONTACT = new Contact("Emre", "https://github.com/emreblbl",
        "emreblblvv@gmail.com");
public static final ApiInfo DEFAULT_API = new ApiInfo("swagger", "Swagger Documentation", "2.0", "urn:tos", CONTACT,
        "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<>());
public static final Set<String> consumes = new HashSet<String>(Arrays.asList("application/json"));
    public static final Set<String> produces = new HashSet<String>(Arrays.asList("application/json"));
@Bean
public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API).consumes(consumes).produces(produces);
}
}
