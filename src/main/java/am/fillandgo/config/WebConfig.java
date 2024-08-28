package am.fillandgo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class represents the configuration for the web application.
 * It implements the WebMvcConfigurer interface to provide custom configuration for Spring MVC.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Adds Cross-Origin Resource Sharing (CORS) mappings to the provided CorsRegistry.
     * CORS allows restricted resources on a web page to be requested from another domain outside the domain from which the resource originated.
     * This method configures the CORS settings for the web application by specifying the allowed origins, methods, headers, and credentials.
     * @param registry the CorsRegistry to which the CORS mappings will be added
     * @see CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  // Use this instead of allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}