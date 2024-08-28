package am.fillandgo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The FillAndGoApplication class is the entry point of the Fill&Go REST API application.
 * It is annotated with the @SpringBootApplication annotation, which indicates that this class is the main Spring Boot application.
 * The @OpenAPIDefinition annotation is used to provide metadata for the OpenAPI documentation.
 * This class contains the main method, which is responsible for starting the application.
 * When the main method is executed, it will start the Spring Boot application by calling the SpringApplication.run() method
 * with the FillAndGoApplication class and the command-line arguments as parameters.
 * Usage example:
 *     FillAndGoApplication.main(args);
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Fill&Go REST API documentation",
                description = "Discover all endpoints and schemas within the application",
                version = "V1",
                contact = @Contact(
                        name = "Ara Hovhannisyan",
                        email = "arahov1986@gmail.com",
                        url = "https://www.linkedin.com/in/ara-hovhannisyan-b96800a1"
                )
        )
)
public class FillAndGoApplication {

    /**
     * The main method is the entry point of the Fill&Go REST API application.
     * It is responsible for starting the application.
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(FillAndGoApplication.class, args);
    }
}