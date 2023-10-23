package com.trabalho.wisan.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Define aqui os domínios permitidos, ou "*" para qualquer origem
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("Origin", "Content-Type", "Accept");
    }
}


