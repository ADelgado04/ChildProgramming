package co.edu.unicauca.child_programming_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Permite acceder a las imágenes desde http://localhost:8080/processImages/{nombre}
        registry.addResourceHandler("/processImages/**")
                .addResourceLocations("file:processImages/");
    }
}
