package br.com.matheus.jwt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.matheus.jwt.model.Employee;
import br.com.matheus.jwt.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/list")
	public ResponseEntity<Object> getAllEmployees() {
		List<Employee> result = employeeService.findAllEmployees();
		
		return ResponseEntity.ok().body(result);
	}

	@GetMapping(value = "/{id}/info")
	public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long idEmployee) {
		Optional<Employee> result = employeeService.findEmployeeById(idEmployee);
		
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping(value = "/save")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
		Employee result = employeeService.saveEmployee(employee);

		return ResponseEntity.ok().body(result);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
		Employee result = employeeService.updateEmployee(employee);
		
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping(value = "/{id}/delete")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Long idEmployee) {
		employeeService.deleteEmployee(idEmployee);
		
		return ResponseEntity.noContent().build();
	}
	
}