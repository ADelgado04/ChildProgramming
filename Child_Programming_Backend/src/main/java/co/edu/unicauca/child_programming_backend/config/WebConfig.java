package co.edu.unicauca.child_programming_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") //endpoints del backend
                        .allowedOrigins("http://localhost:3000") // frontend con Vite
                        .allowedMethods("GET", "POST", "PUT", "DELETE") //los métodos permitidos
                        .allowedHeaders("*"); //todos los headers
            }
        };
    }
}
