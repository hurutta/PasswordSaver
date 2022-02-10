package com.example.PasswordSaver;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class RestServiceCorsApplication {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) 
			{
				registry.addMapping("/create").allowedOrigins("http://localhost:8080/");
    			//registry.addMapping("/remove/{name}").allowedOrigins("https://pass-word-saver.herokuapp.com");
			}
		};
	}
}
