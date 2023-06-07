package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SpringBootApplication
@RequestMapping("/api/v1/customers")
public class AmigocourseApplication{

	private final CustomerRepository customerRepository;

	public AmigocourseApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AmigocourseApplication.class, args);
	}

	@GetMapping
	public List<Customer> getCustomers(){
		return customerRepository.findAll();
	}

	record NewCustomerRequest(
			String name,
			String email,
			Integer age
	){

	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName((request.name()));
		customer.setEmail((request.email()));
		customer.setAge((request.age()));
		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}

	record updateData(
			String name,
			Integer age,
			String email
	){

	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id,@RequestBody updateData request){
		Customer customer = customerRepository.findById(id).get();
		customer.setName((request.name()));
		customer.setEmail((request.email()));
		customer.setAge((request.age()));
		customerRepository.save(customer);

	}




}
