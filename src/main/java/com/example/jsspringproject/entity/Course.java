package com.example.jsspringproject.entity;

import jakarta.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "course")
public class Course {

	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long course_id;
	    
	    @Column
	    private String name;
	    
	    @ManyToOne
	    @JoinColumn(name = "student_id")
	    @JsonBackReference
	    private Student student;
	    
	    

	    public Long getCourse_id() {
	        return course_id;
	    }

	    public void setCourse_id(Long course_id) {
	        this.course_id = course_id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public Student getStudent() {
	        return student;
	    }

	    public void setStudent(Student student) {
	        this.student = student;
	    }

	    public Course(Long course_id, String name, Student student) {
	        super();
	        this.course_id = course_id;
	        this.name = name;
	        this.student = student;
	    }

	    public Course(String name, Student student) {
	        super();
	        this.name = name;
	        this.student = student;
	    }

	    public Course() {
	        super();
	    }
}
