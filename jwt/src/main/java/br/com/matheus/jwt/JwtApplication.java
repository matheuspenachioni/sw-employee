package br.com.matheus.jwt;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.matheus.jwt.model.Employee;
import br.com.matheus.jwt.repository.EmployeeRepository;

@SpringBootApplication
public class JwtApplication {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
		
	}
	
	//Pequena peripécia para poder testar o login...
	@Component
	public class EmployeeInitializer implements CommandLineRunner {

	    @Override
	    public void run(String[] args) throws Exception {
	    		Employee emp1 = new Employee();
	    		emp1.setNameEmployee("Matheus Penachioni");
	    		emp1.setCpfEmployee("90334707056");
	    		emp1.setEmailEmployee("matheus@mail.com");
	    		emp1.setPasswordEmployee(encoder.encode("123"));
	    		emp1.setDateCreated(LocalDateTime.now());
	    		emp1.setDateUpdated(LocalDateTime.now());
	    		
	    		employeeRepository.save(emp1);
	    }
	}

}
