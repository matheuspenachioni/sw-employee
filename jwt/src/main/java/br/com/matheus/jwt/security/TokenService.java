package br.com.matheus.jwt.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.matheus.jwt.model.Employee;

@Service
public class TokenService {

	public String gerarToken(Employee user) {
		return JWT.create()                                           // Cria um token
				.withIssuer("AuthUser").withSubject(                  // com um issuer AuthUser no payload
						user.getEmailEmployee())                      // e passa o e-mail como subject
				.withClaim("id", user.getIdEmployee()).withExpiresAt( // Passa o id do Employe no claim e e diz que
						LocalDateTime.now().plusMinutes(30)           // o token expira em 30 minutos depois de ser criado
						.toInstant(ZoneOffset.of("-03:00")            // e especifica a zona em que é contabilizada esse tempo
				)).sign(Algorithm.HMAC256("SecurityHash"));           // Palavra-chve pro Hash do tipo HMAC256
	}

	public Object getSubject(String token) {
		return JWT.require(Algorithm.HMAC256("SecurityHash"))         // Requer a palavra-chve pro Hash do tipo HMAC256
				.withIssuer("AuthUser").build()                       // com o mesmo issuer AuthUser,
				.verify(token).getSubject();                          // verifica o token e pega o subject
	}

}
