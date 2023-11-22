package com.example.jsspringproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.example.jsspringproject.entity.Course;
import com.example.jsspringproject.entity.Student;
import com.example.jsspringproject.model.Studentmodel;
import com.example.jsspringproject.repository.StudentRepository;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class Studentservice {
	
	@Autowired
    StudentRepository StudentRepo;
	
	private final Logger logger = LoggerFactory.getLogger(Studentservice.class);
	
	
	
	//use this
	
	  public ResponseEntity<String> addStudent(Student student) {
	        ResponseEntity<String> response;
	        try {
	          StudentRepo.save(student);
	           response =  ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student Record Created Successfully on Database");
	        }catch(Exception ex) {
	            System.out.println(ex.getMessage());
	            response = ResponseEntity.status(HttpStatusCode.valueOf(500)).body("Error Creating Student record");
	        }
	        return response;
	    }
	  
	  
	  //alternate
	  
	   public ResponseEntity<String> addStudents(Map<String, Object> formData) {
	        try {
	            // Convert and save the data in the repository
	            Student student = convertFormDataToStudent(formData);
	            StudentRepo.save(student);
	            return new ResponseEntity<>("Student added successfully", HttpStatus.CREATED);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	   
	   //alternate
	   
	   private Student convertFormDataToStudent(Map<String, Object> formData) {
	        Student student = new Student();

	        // Extract user information
	        Map<String, Object> userInfo = (Map<String, Object>) formData.get("userInfo");
	        student.setFirstName((String) userInfo.get("firstName"));
	        student.setLastName((String) userInfo.get("lastName"));
	        student.setAddress((String) userInfo.get("address"));
	        student.setPhone((String) userInfo.get("phone"));
	        student.setDob((String) userInfo.get("dob"));
	        student.setGender((String) userInfo.get("gender"));
	        student.setEmail((String) userInfo.get("email"));

	        // Extract academic information
	        Map<String, Object> academicInfo = (Map<String, Object>) formData.get("academicInfo");
	        List<String> technicalSkills = (List<String>) academicInfo.get("technicalSkills");

	        // Create courses
	        List<Course> courses = technicalSkills.stream()
	                .map(courseName -> {
	                    Course course = new Course();
	                    course.setName(courseName);
	                    course.setStudent(student);
	                    return course;
	                })
	                .collect(Collectors.toList());

	        // Set courses in the student entity
	        student.setCourses(courses);
	    

	        return student;
	    }
	   
	   //use
	   
	   public List<Student> getAllStudents() {
	        List<Student> students=StudentRepo.findAll();
	        logger.info("All Students :"+students);
	        return students;
	    } 
	   
	   
	   public ResponseEntity<List<Studentmodel>> getAllStudentss() {
	        try {
	            List<Student> students = StudentRepo.findAll();
	            List<Studentmodel> studentDTOs = students.stream().map(this::convertStudentToDTO).collect(Collectors.toList());
	            return new ResponseEntity<>(studentDTOs, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    private Studentmodel convertStudentToDTO(Student student) {
	    
	    	Studentmodel studentDTO = new Studentmodel();
	        studentDTO.setStudent_id(student.getStudent_id());
	        studentDTO.setFirstName(student.getFirstName());
	        studentDTO.setLastName(student.getLastName());
	        studentDTO.setEmail(student.getEmail());
	        
	        studentDTO.setPhone(student.getPhone());    
	        studentDTO.setDob(student.getDob());
	        studentDTO.setGender(student.getGender());           
	        studentDTO.setAddress(student.getAddress());           
//	        studentDTO.setCourses(student.getCourses());
	      
	        return studentDTO;
	    }
	    
	    //use
	    
	    public void update(Studentmodel std,long id) {
	    	Optional<Student> singleStudent = StudentRepo.findById(id);
	    	
	    	   if (singleStudent.isPresent()) {
	    		   Student thatStudent = singleStudent.get();
	    		   
	    		   thatStudent.setFirstName(std.getFirstName());
	    		   thatStudent.setLastName(std.getLastName());
	    		   thatStudent.setEmail(std.getEmail());
	    		   thatStudent.setPhone(std.getPhone());
//	    		   thatStudent.setDob(std.getDob());
//	   	     thatStudent.setGender(std.getGender());
	   	  thatStudent.setAddress(std.getAddress());
	   	  
	       	 	StudentRepo.save(thatStudent);
	    	   }else {
	   	        System.out.println("Student not found with  this id: " + id);
	   	    }
	    	
			
		}
	    
	    public Optional<Student> getById(long id) {
			return StudentRepo.findById(id);
//					
		}
	    
	    
	    public void delete(long id) {
	    	StudentRepo.deleteById(id);
		}
	   

	

}
