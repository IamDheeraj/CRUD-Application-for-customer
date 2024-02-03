package com.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.customer.entity.Customer;
//
//private String street;
//private String address;
//private String city;
//private String state;
//private String email;
//private String phone;
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	
// Creating methods for searching 
	
	List<Customer> findByFirstname(String firstname);
	List<Customer> findByLastname(String lastname);
	List<Customer> findByStreet(String street);
	List<Customer> findByAddress(String address);
	List<Customer> findByCity(String city);
	List<Customer> findByState(String state);

}
