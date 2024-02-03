package com.customer.controller; // package for customer controller

// importing all necessary interface and classes
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;

@RestController // declaring class as restController
@RequestMapping("/api") // starting url of application
public class CustomerController {

	// Getting object reference of customerRepository
	@Autowired
	CustomerRepository customerrepo;

	// Method for creating a customer
	@PostMapping("/customer") // Using post method for creating data
	public String createCustomer(@RequestBody Customer customer) {
		customerrepo.save(customer);
		return "Customer Created successfully..."; // returning http status
	}

	// Method for getting information for single customer with help of id number
	@GetMapping("/customer/{Id}") // Using get method for fetching information
	public ResponseEntity<Customer> getCustomer(@PathVariable int Id) {
		Optional<Customer> customerId = customerrepo.findById(Id);
		if (customerId.isPresent()) {
			return new ResponseEntity<Customer>(customerId.get(), HttpStatus.FOUND); // returning http status
		} else {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND); // returning http status
		}

	}

	// Method for getting information for all customer
	@GetMapping("/customer") // Using get method for fetching information
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> customerList = new ArrayList<>();
		customerrepo.findAll().forEach(customerList::add);
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK); // returning http status
	}

	// method for pagination and sorting
	@GetMapping("/page")
	public ResponseEntity<Page<Customer>> customerPage(@PageableDefault(size = 5, sort = "Id") Pageable pageable) {
		Page<Customer> customerPage = customerrepo.findAll(pageable);

		return ResponseEntity.ok(customerPage);
	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/firstname/{firstname}")
	public List<Customer> getCustomerByFirstName(@PathVariable(name = "firstname") String firstName) {

		return customerrepo.findByFirstname(firstName);
	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/lastname/{lastname}")
	public List<Customer> getCustomerByLastName(@PathVariable(name = "lastname") String LastName) {

		return customerrepo.findByLastname(LastName);
	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/street/{street}")
	public List<Customer> getCustomerByStreet(@PathVariable(name = "street") String street) {

		return customerrepo.findByStreet(street);
	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/address/{name}")
	public List<Customer> getCustomerByAddress(@PathVariable(name = "name") String address) {
		return customerrepo.findByAddress(address);

	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/city/{city}")
	public List<Customer> getCustomerByCity(@PathVariable(name = "city") String city) {

		return customerrepo.findByCity(city);
	}

	// Methods for searching by name and filtering data by name
	@GetMapping("/state/{state}")
	public List<Customer> getCustomerByState(@PathVariable(name = "state") String state) {

		return customerrepo.findByState(state);
	}

	// method for updating customer credentials
	@PutMapping("/customer/{Id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable int Id, @RequestBody Customer customer) {
		Optional<Customer> customerExist = customerrepo.findById(Id);
		if (customerExist.isPresent()) { // checking value is present or not

			// setting value for all fields
			Customer customer1 = customerExist.get();
			customer1.setFirstname(customer.getFirstname());
			customer1.setLastname(customer.getLastname());
			customer1.setStreet(customer.getStreet());
			customer1.setAddress(customer.getAddress());
			customer1.setCity(customer.getCity());
			customer1.setState(customer.getState());
			customer1.setEmailAddress(customer.getEmailAddress());
			customer1.setPhone(customer.getPhone());

			customerrepo.save(customer1);
			return new ResponseEntity<Customer>(HttpStatus.ACCEPTED); // returning http status
		} else {
			return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST); // returning http status
		}
	}

	// Using delete method for removing data from the database
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int Id) {
		Optional<Customer> customerExist = customerrepo.findById(Id);
		if (customerExist.isPresent()) {
			customerrepo.deleteById(Id);
			return new ResponseEntity<Customer>(HttpStatus.GONE); // returning http status
		} else {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND); // returning http status
		}
	}

	// Using delete method for removing data from the database
	@DeleteMapping("/deleteall")
	public String deleteAllCustomer() {
		customerrepo.deleteAll();
		String a = "all book deleted ";
		return "All records cleared ";
	}
}
