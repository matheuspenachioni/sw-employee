package br.com.matheus.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheus.jwt.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
