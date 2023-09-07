package com.crud.service;


import com.crud.entity.AverageAgeResult;
import com.crud.entity.Student;
import com.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private final MongoTemplate mongoTemplate;
    public StudentService(StudentRepository studentRepository, MongoTemplate mongoTemplate) 
    {
        this.studentRepository = studentRepository;
        this.mongoTemplate = mongoTemplate;
    }
    
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(int id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }
    public double calculateAverageAge() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.group().avg("age").as("averageAge")//group operator
        );

        AverageAgeResult result = mongoTemplate.aggregate(aggregation, Student.class, AverageAgeResult.class)
                                             .getUniqueMappedResult();

        if (result != null) {
            return result.getAverageAge();
        } else {
            return 0; 
        }
    }
    
    public Student getStudentByUuid(UUID uuid) {
        return studentRepository.findByUuid(uuid);
    }
   
   
}
    
    


