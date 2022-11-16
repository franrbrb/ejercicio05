package com.curso.inicio;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	//definición roles y usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
        .inMemoryAuthentication()
        .withUser("user1")
          .password("{noop}user1")
          .roles("INVITADO")
          .and()
        .withUser("user2")
          .password("{noop}user2")
          .roles("OPERADOR")
          .and()
        .withUser("user3")
          .password("{noop}user3")
          .roles("ADMIN")
          .and()
          .withUser("user4")
          .password("{noop}user4")
          .roles("OPERADOR", "ADMIN");
	}
	
	//definición de políticas de seguridad
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		//Sólo los miembros del rol Admin pueden dar de alta cursos
		.antMatchers(HttpMethod.POST,"/curso").hasRole("ADMIN")
		//Sólo los miembros de los roles Admin y Operador pueden realizar eliminaciones y actualizaciones de duración
		.antMatchers(HttpMethod.DELETE).hasAnyRole("ADMIN", "OPERADOR")
		.antMatchers(HttpMethod.GET, "/curso/**/**").hasAnyRole("ADMIN", "OPERADOR")
		//// buscar por codigo
		.antMatchers(HttpMethod.GET, "/curso/*").authenticated()	
		// devuelve lista
		//.antMatchers(HttpMethod.GET, "/curso").authenticated()	
		.antMatchers("/cursos").authenticated()	
		.and()
		.httpBasic();
	
	}
}