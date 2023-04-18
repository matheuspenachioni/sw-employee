package br.com.matheus.jwt.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_employee", nullable = false)
	private Long idEmployee;
	
	@Column(name = "name_employee", nullable = false)
	private String nameEmployee;
	
	@CPF
	@Column(name = "cpf_employee", nullable = false, unique = true)
	private String cpfEmployee;
	
	@Email
    @Column(name = "email_customer", nullable = false, unique = true)
    private String emailEmployee;
    
    @Column(name = "password_customer", nullable = false)
    private String passwordEmployee;

	@Column(name = "date_created", nullable = false, updatable = false)
	@JsonFormat(pattern = "dd/MM/yy HH:mm:ss")
	private LocalDateTime dateCreated;
	
	@Column(name = "date_updated", nullable = false)
	@JsonFormat(pattern = "dd/MM/yy HH:mm:ss")
	private LocalDateTime dateUpdated;

	@PrePersist
	public void prePersist() {
		this.setDateCreated(LocalDateTime.now());
		this.setDateUpdated(LocalDateTime.now());
	}
	
	@PreUpdate
	public void preUpdate() {
		this.setDateUpdated(LocalDateTime.now());
	}
}
