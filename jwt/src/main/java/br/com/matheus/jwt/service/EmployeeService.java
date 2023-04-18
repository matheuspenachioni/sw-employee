package br.com.matheus.jwt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.matheus.jwt.model.Employee;
import br.com.matheus.jwt.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	
	public Optional<Employee> findEmployeeById(Long idEmployee) {
		if(idEmployee == null || idEmployee <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!");
		}
		
		return employeeRepository.findById(idEmployee);
	}
	
	public Employee saveEmployee(Employee employee) {
		if(validateEmployee(employee)) {
			return employeeRepository.saveAndFlush(employee);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Name or CPF are not valid!");
		}
	}
	
	public Employee updateEmployee(Employee employee) {
		if(employee.getIdEmployee() == null || employee.getIdEmployee() <= 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee ID is required in the update!");
		}

		if(validateEmployee(employee) && validateCpfEmployee(employee)) {
			return employeeRepository.saveAndFlush(employee);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Name or CPF are not valid!");
		}
	}
	
	public void deleteEmployee(Long idEmployee) {
		if(idEmployee == null || idEmployee <= 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!");
		}
		
		Optional<Employee> employee = findEmployeeById(idEmployee);
		
		employeeRepository.delete(employee.get());
	}
	
	public Boolean validateEmployee(Employee employee) {
		if(employee.getNameEmployee() != null && employee.getCpfEmployee() != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean validateCpfEmployee(Employee employee) {
		if(employeeRepository.findById(employee.getIdEmployee()).get().getIdEmployee() != employee.getIdEmployee()) {
			return false;
		} else {
			return true;
		}
	}
}