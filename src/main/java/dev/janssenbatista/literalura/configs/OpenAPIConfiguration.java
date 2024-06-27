package dev.janssenbatista.literalura.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "LiterAlura",
                description = "API para busca e armazenamento de livros",
                version = "1.0.0",
                contact = @Contact(
                        name = "Janssen Batista",
                        email = "batistajanssen.dev@gmail.com",
                        url = "https://github.com/janssenbatista"
                )
        )
)
public class OpenAPIConfiguration {
}
