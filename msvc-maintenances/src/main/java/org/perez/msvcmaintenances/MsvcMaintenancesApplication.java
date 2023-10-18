package org.perez.msvcmaintenances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableFeignClients
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MsvcMaintenancesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcMaintenancesApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "POST", "DELETE")
						.allowedHeaders("Access-Control-Allow-Origin", "Content-Type", "Authorization");
			}
		};
	}
}
