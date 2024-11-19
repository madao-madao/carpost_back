package org.sark.carpost.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Разрешаем доступ ко всем эндпоинтам
                .allowedOrigins("http://localhost:5173")  // Разрешаем запросы с localhost:5173
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // Разрешенные методы
                .allowedHeaders("*")  // Разрешаем все заголовки
                .allowCredentials(true);  // Разрешаем использование куки и учетных данных
    }
}