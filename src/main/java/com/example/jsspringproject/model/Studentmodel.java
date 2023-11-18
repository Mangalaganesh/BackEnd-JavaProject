package com.example.jsspringproject.model;

import java.util.List;
import com.example.jsspringproject.entity.Course;


public class Studentmodel {
	
	 private Long student_id;
     
     private String firstName;

     private String lastName;

     private String address;    

     private String phone;

     private String dob;

     private String gender;

     private String email;

     private byte[] fileData;
     
     private List<Course> courses;
     
     

     public byte[] getFileData() {
         return fileData;
     }
     public void setFileData(byte[] fileData) {
         this.fileData=fileData;
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


     public List<Course> getCourses() {
         return courses;
     }

     public void setCourses(List<Course> courses) {
         this.courses = courses;
     }

     @Override
     public String toString() {
         return "Studentmodel [student_id=" + student_id + ", firstName=" + firstName + ", lastName=" + lastName
                 + ", address=" + address + 
                 ", phone=" + phone + ", dob=" + dob + ", gender=" + gender + ", email=" + email + 
               ", courses=" + courses + "]";
     }
     

}
