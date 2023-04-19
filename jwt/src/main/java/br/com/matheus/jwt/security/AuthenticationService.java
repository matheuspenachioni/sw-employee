package br.com.matheus.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.matheus.jwt.repository.EmployeeRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // Sobrescreve o método loadUserByUsername passando
		                                                                                   // o e-mail como o parâmetro
		return employeeRepository.findByEmailEmployee(email);                              // E então procura um Employee pelo e-mail
	}	

}
