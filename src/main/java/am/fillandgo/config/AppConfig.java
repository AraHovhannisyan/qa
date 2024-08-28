package am.fillandgo.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * This class is the configuration class for the application.
 * It is marked with the {@link Configuration} annotation, indicating that it is responsible
 * for defining and configuring beans for the application.
 */
@Configuration
public class AppConfig {

    @PostConstruct
    public void init() {

        // Install the SLF4JBridgeHandler
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}
