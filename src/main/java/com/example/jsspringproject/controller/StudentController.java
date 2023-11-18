package com.example.jsspringproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.jsspringproject.entity.Course;
import com.example.jsspringproject.entity.Student;
import com.example.jsspringproject.exception.ResourceNotFoundException;
import com.example.jsspringproject.model.Studentmodel;
import com.example.jsspringproject.repository.StudentRepository;
import com.example.jsspringproject.service.Studentservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200/*")
public class StudentController {
	
	@Autowired
    Studentservice studentService;
	
    private final Logger logger=LoggerFactory.getLogger(Studentservice.class);
    
    
    
    @PostMapping("/add")
    public ResponseEntity<String> addNewStudent(@RequestParam("dataToSend") String formdata, 
    		@RequestParam ("file") MultipartFile file) throws JsonMappingException, JsonProcessingException
    
//    		public ResponseEntity<String> addNewStudent(@RequestParam  String formdata,
//    				@RequestParam ("file") MultipartFile file)
    		{
    	try {
//				Map<String, Map<String, Object>> map = (Map<String, Map<String, Object>>)formdata;
    	ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper from Jackson library
        Map<String, Map<String, Object>> formDataMap = objectMapper.readValue(formdata, new TypeReference<Map<String, Map<String, Object>>>() {});
        
        Student student =new Student();
        Map<String, Object> form1data = formDataMap.get("userInfo");
        Map<String, Object> form2data=(Map<String, Object>)formDataMap.get("academicInfo");
        
        List<String> allCourses=(List<String>) form2data.get("selectedOptions");
        List<Course> courses=allCourses.stream().map(courseName->{
        	
            Course course=new Course();
            
                course.setName(courseName);
                course.setStudent(student);
                return course;
                
        }).collect(Collectors.toList());
        
        student.setFirstName((String)form1data.get("firstName"));
      student.setLastName((String)form1data.get("lastName")); 
      student.setEmail((String)form1data.get("email"));
      student.setPhone((String)form1data.get("phone"));
      student.setAddress((String)form1data.get("address"));
      student.setDob((String)form1data.get("dob"));
      student.setGender((String)form1data.get("gender"));
      student.setCourses(courses);
      
      System.out.println("Student request:"+student);
      
      if(file!=null) {
        byte[] fileData=file.getBytes();
        student.setFileData(fileData);
    }
      return studentService.addStudent(student);
      
          }
    catch(Exception e) {
    	
    	        
    	        logger.error(e.getMessage());
    	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	        }
    			
    			
    		}
			
//    {
//    try {     
//    	 ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper from Jackson library
//         Map<String, Object> formDataMap = objectMapper.readValue(formdata, new TypeReference<Map<String, Object>>() {});
//    	
//    Map<String, Object> form1data = (Map<String, Object>) formDataMap.get("userInfo");
//    Map<String, Object> form2data=(Map<String, Object>)formDataMap.get("academicInfo");
//    
//    Student student =new Student();
//    
//    List<String> allCourses=(List<String>) form2data.get("technicalSkills");
//    
//    List<Course> courses=allCourses.stream().map(courseName->{
//    	
//        Course course=new Course();
//        
//            course.setName(courseName);
//            course.setStudent(student);
//            return course;
//            
//    }).collect(Collectors.toList());
//    
//    
//    student.setFirstName((String)form1data.get("firstName"));
//    student.setLastName((String)form1data.get("lastName")); 
//    student.setEmail((String)form1data.get("email"));
//    student.setPhone((String)form1data.get("phone"));
//    student.setAddress((String)form1data.get("address"));
//    student.setDob((String)form1data.get("dob"));
//    student.setGender((String)form1data.get("gender"));
//    student.setCourses(courses);
//    
//    System.out.println("Student request:"+student);
//    
//    if(file!=null) {
//        byte[] fileData=file.getBytes();
//        student.setFileData(fileData);
//    }
//
//
//    return studentService.addStudent(student);
//
//    }
//
//    catch(Exception e) {
//
//        
//        logger.error(e.getMessage());
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//}
//    
//    
//    @PostMapping("//")
//    public ResponseEntity<String> addNewStudents(@RequestBody Map<String, Object> formData ) {
//        try {
//            return studentService.addStudents(formData);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    
    
    @GetMapping("/getall")
    public ResponseEntity<List<Studentmodel>> getAll()
    {
        List<Student> allStudents=studentService.getAllStudents();
        
        List<Studentmodel> studentmodels=allStudents.stream().map(student ->{
            
        	Studentmodel sdto=new Studentmodel();

            sdto.setFirstName(student.getFirstName());
            sdto.setLastName(student.getLastName()); 
            sdto.setEmail(student.getEmail());
            sdto.setPhone(student.getPhone());    
            sdto.setDob(student.getDob());
            sdto.setGender(student.getGender());           
            sdto.setAddress(student.getAddress());  
            sdto.setStudent_id(student.getStudent_id());
            //sdto.setFileData(student.getFileData());
            sdto.setCourses(student.getCourses());
            
            logger.info("DTO: ",sdto);
            return sdto;
            
        }).collect(Collectors.toList());
        
        return  new ResponseEntity<>(studentmodels,HttpStatus.OK);
    }
    
    
    @GetMapping("//")
    public ResponseEntity<List<Studentmodel>> findstudent() {
        return studentService.getAllStudentss();
    }
    
    
    @PutMapping("update/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Student studentdata, @PathVariable int id) {
    	try {
    		studentService.update(studentdata, id);
       	 return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Student_detail Updated Successfully");
    		
    	}catch (Exception ex) {
    	    System.out.println(ex.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occur");
    		
    	}
    	 
		
	}
    
    @GetMapping("getbyid/{id}")
	public Optional<Student> getStudentById(@PathVariable long id) {
		Optional<Student> std = studentService.getById(id);
		System.out.println("Student_detail " + std);
		return std;
	}
    
    @DeleteMapping("delete/{id}")
	public void deleteEmployee(@PathVariable long id) {
    	studentService.delete(id);

	}
	
	
	
	
//    @Autowired
//	private StudentRepository studentRepository;
//	
//	@GetMapping("/employees")
//	public List<Student> getAllEmployees(){
//		return studentRepository.findAll();
//	}	
//	
//	@PostMapping("/employees")
//	public Student createEmployee(@RequestBody Student student) {
//		return studentRepository.save(student);
//	}
//	
//	@GetMapping("/employees/{id}")
//	public ResponseEntity<Student> getEmployeeById(@PathVariable Long id) {
//		Student employee = studentRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		return ResponseEntity.ok(employee);
//	}
//	
//	@PutMapping("/employees/{id}")
//	public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
//		Student student = studentRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with  this id :" + id));
//		
//		student.setFirstName(studentDetails.getFirstName());
//		student.setLastName(studentDetails.getLastName());
//		student.setEmail(studentDetails.getEmail());
//		
//		Student updatedEmployee = studentRepository.save(student);
//		return ResponseEntity.ok(updatedEmployee);
//	}
//
//	
//	@DeleteMapping("/employees/{id}")
//	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
//		Student employee = studentRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
//		
//		studentRepository.delete(employee);
//		Map<String, Boolean> response = new HashMap<>();
//		response.put("deleted", Boolean.TRUE);
//		return ResponseEntity.ok(response);
//	}
}
