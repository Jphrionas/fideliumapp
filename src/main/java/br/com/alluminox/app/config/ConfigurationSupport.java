package br.com.alluminox.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ConfigurationSupport extends WebMvcConfigurationSupport{

	// Configurar o PageRequest
	
	// Configurar o Spl para o Spring Security in @Query
	


	@Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error403").setViewName("/error/403");
	}

}
