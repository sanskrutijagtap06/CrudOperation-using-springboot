package com.crud.entity;

import java.sql.Date;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Document
public class Student {

	@Id
	int id;
	String name;
	private int age;
	  @Column(columnDefinition = "BINARY(16)") // Store UUID as binary data
	    private UUID uuid = UUID.randomUUID(); 
	  
	  @CreatedDate
	    
	  private Date timestampMillis;
	  
	
	public Date getTimestampMillis() {
		return timestampMillis;
	}
	public void setTimestampMillis(Date date) {
		this.timestampMillis = date;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
