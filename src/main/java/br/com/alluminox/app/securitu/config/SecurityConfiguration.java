package br.com.alluminox.app.securitu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.alluminox.app.io.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configurable
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder passwordEncoder;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/home").hasAnyRole("ADMIN", "USER")
		.antMatchers("/user/admin/**/*").hasRole("ADMIN")
		.antMatchers("/user").hasRole("ADMIN")
		.antMatchers("/user/new").hasRole("ADMIN")
		.antMatchers("/reward/*/new").hasAnyRole("USER", "ADMIN")
		
		.antMatchers(HttpMethod.GET, "/user/*").hasAnyRole("ADMIN", "USER")
		.anyRequest()
		.authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.logoutUrl("/logout")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/error403");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//UserBuilder users = User.withDefaultPasswordEncoder();
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	}
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
