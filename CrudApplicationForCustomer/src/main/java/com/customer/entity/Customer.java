package com.customer.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity  // declaring class as entity 
@Data
@AllArgsConstructor  // creating constructor with arguments 
@NoArgsConstructor   // creating default constructor 
public class Customer {
	
	// Creating all necessary fields for customer
	@Id   // Declaring fields as id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // generated value annotations applied for auto increament of id 
	private int Id;
	private String firstname;
	private String lastname;
	private String street;
	private String address;
	private String city;
	private String state;
	private String emailAddress;
	private String phone;
	
}
