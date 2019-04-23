package br.com.alluminox.app.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.alluminox.app.RewardsAppApplication;

public class WebInitilizer extends SpringBootServletInitializer {
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RewardsAppApplication.class);
    }    
}