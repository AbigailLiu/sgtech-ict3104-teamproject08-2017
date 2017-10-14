package com.lifestyle.stps;

import com.lifestyle.stps.Configurations.JerseyConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LifeStyleApplication {

	public static void main(String[] args) {
		SpringApplication.run(LifeStyleApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new ServletContainer(), "/rest/*");
		registrationBean.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
		return registrationBean;
	}
}
