package ru.itmo.sdcourse.hw4;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itmo.sdcourse.hw4.controller.aop.auth.AuthenticationInterceptor;

@SpringBootApplication
public class Hw4Application implements WebMvcConfigurer {
	private AuthenticationInterceptor authenticationInterceptor;

	@Autowired
	public void setAuthenticationInterceptor(AuthenticationInterceptor authenticationInterceptor) {
		this.authenticationInterceptor = authenticationInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor);
	}

	public static void main(String[] args) {
		SpringApplication.run(Hw4Application.class, args);
	}
}



