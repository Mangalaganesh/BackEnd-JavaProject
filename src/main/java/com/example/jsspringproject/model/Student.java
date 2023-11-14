package com.example.jsspringproject.model;



import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "student")
public class Student  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long student_id;
	
    
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String address;    
    
    @Column
    private String phone;

    @Column
    private String dob;

    @Column
    private String gender;

    @Column
    private String email;

	@Column
    private List<String> courses;
   

    

    @Column(name = "file_data", columnDefinition = "BLOB", length = 10485760) // Adjust the length accordingly
    private byte[] fileData;


    
    public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}





    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    

    public Student() {
        super();
    }

//    public Student(Long student_id, String firstName, String lastName, String address,
//             String phone, String dob, String gender, String email, 
//              byte[] fileData, List<Course> courses) {
//        super();
//        this.student_id = student_id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//      
//        this.phone = phone;
//        this.dob = dob;
//        this.gender = gender;
//        this.email = email;
//       
//        this.fileData = fileData;
//        this.courses = courses;
//    }

//    public Student(String firstName, String lastName, String address, 
//            String phone, String dob, String gender, String email, 
//             byte[] fileData, List<Course> courses) {
//        super();
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//       
//        this.phone = phone;
//        this.dob = dob;
//        this.gender = gender;
//        this.email = email;
//     
//        this.fileData = fileData;
//        this.courses = courses;
//    }
}
