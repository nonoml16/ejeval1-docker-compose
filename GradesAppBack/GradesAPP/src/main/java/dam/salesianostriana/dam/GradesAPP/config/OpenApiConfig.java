package dam.salesianostriana.dam.GradesAPP.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("GradesAPI")
                .description("API REST para la gestion de alumnos y asignaturas")
                .version("1.0")
                .license(new License().name("Creative Commons Zero v1.0 Universal").url("https://github.com/GradesTeam/GradesAppBack")));
    }
}
