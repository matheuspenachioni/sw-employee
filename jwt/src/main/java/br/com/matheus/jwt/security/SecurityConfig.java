package br.com.matheus.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity                                                                   // Diz que essa é uma classe de configuração da Web e 
                                                                                     // do SpringSecurity
public class SecurityConfig {

	@Autowired
	private FilterToken filter;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf().disable().sessionManagement()                     // Desabilita a proteção que evita ataques CSRF e
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)              // desabilita a criação de sessão para assegurar que todas as 
																					 // requisições sejam tratadas sem uma sessão
				.and().authorizeHttpRequests()                                       // Configura a autorização de requisições HTTP
				.requestMatchers(HttpMethod.POST, "/login").permitAll()              // e permite o usuário não-autenticado o acesso ao  
				                                                                     // endpoint de login via método POST
				.anyRequest().authenticated().and()                                  // Requer autenticação para todas as outras requisições 
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class) // Adiciona um filtro personalizado antes do 
																					 // filtro padrão de autenticação
				.build();                                                            // e constrói o objeto SecurityFilterChain
	}
	
	@Bean
	public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();               // retorna uma nova configuração de autenticação
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();                                          // retorna um nova instância de BCryptPassowrdEncoder 
                                                                                     // para criptografar senhas
    }
}
