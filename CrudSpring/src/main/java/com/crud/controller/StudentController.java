package com.crud.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Student;
import com.crud.service.StudentService;

@RestController
@RequestMapping("/mongo")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	 @GetMapping("/endpoint")
	    public ResponseEntity<String> getEndpoint() {
	        return ResponseEntity.ok("   *********************This is the GET response.");
	        
	    }
	 @PostMapping("/addStudent")
	    public Student addStudent(@RequestBody Student student) {
	        return studentService.addStudent(student);
	    }
	    

	    @GetMapping("/getAllStudents")
	    public List<Student> getAllStudents() {
	        return studentService.getAllStudents();
	    }

	    @GetMapping("/{id}")
	    public Student getStudentById(@PathVariable int id) {
	        return studentService.getStudentById(id);
	    }

	    @DeleteMapping("/{id}")
	    public void deleteStudentById(@PathVariable int id) {
	        studentService.deleteStudentById(id);
	    }

	    @PutMapping("/{id}")
	    public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
	        return studentService.updateStudent(id, student);
	    }
	    @GetMapping("/average-age")
	    public double getAverageAge() {
	        return studentService.calculateAverageAge();
	}
	    @GetMapping("/{uuid}")
	    public ResponseEntity<Student> getStudentByUuid(@PathVariable UUID uuid) {
	        Student student = studentService.getStudentByUuid(uuid);
	        if (student != null) {
	            return ResponseEntity.ok(student);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	  
	   
}
