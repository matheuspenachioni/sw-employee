package br.com.matheus.jwt.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.matheus.jwt.repository.EmployeeRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token;
		var authorizationHeader = request.getHeader("Authorization"); // Pega a autorização no cabeçalho
		
		if(authorizationHeader != null) {                             // Verifica se a autorização que veio do cabeçalho não é vazia
			token = authorizationHeader.replace("Bearer ", "");       // Substitui "Bearer " que vem por padrão por nada
			var subject = this.tokenService.getSubject(token);        // Chama o método getSubject passando o token
			var user = this.employeeRepository.findByEmailEmployee((String) subject); // Procura um Employee pelo e-mail com cast em String
			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); // Cria uma nova autenticaçãao do
			                                                          // tipo usuário e senha passando o user e suas autorizações
			
			SecurityContextHolder.getContext().setAuthentication(authentication);     // Pega o contexto e insere o authentication na
			                                                                          // autorização do cabeçalho
		}
		
		filterChain.doFilter(request, response);                      // Passa por uma cadeia de filtros
	}
	

}