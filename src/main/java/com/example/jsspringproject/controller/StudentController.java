package com.example.jsspringproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jsspringproject.exception.ResourceNotFoundException;
import com.example.jsspringproject.model.Student;
import com.example.jsspringproject.repository.StudentRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@GetMapping("/employees")
	public List<Student> getAllEmployees(){
		return studentRepository.findAll();
	}	
	
	@PostMapping("/employees")
	public Student createEmployee(@RequestBody Student student) {
		return studentRepository.save(student);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Student> getEmployeeById(@PathVariable Long id) {
		Student employee = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with  this id :" + id));
		
		student.setFirstName(studentDetails.getFirstName());
		student.setLastName(studentDetails.getLastName());
		student.setEmail(studentDetails.getEmail());
		
		Student updatedEmployee = studentRepository.save(student);
		return ResponseEntity.ok(updatedEmployee);
	}

	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		Student employee = studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		studentRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
