package br.com.matheus.jwt.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails {
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
    @Column(name = "email_employee", nullable = false, unique = true)
    private String emailEmployee;
    
    @Column(name = "password_employee", nullable = false)
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwordEmployee;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return emailEmployee;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
