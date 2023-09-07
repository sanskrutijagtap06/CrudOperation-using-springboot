package com.crud.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crud.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {
	 
Student findByUuid(UUID uuid);

		
	

}
