package com.Adi.regitration.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Adi.regitration.Entity.Customer;
import com.Adi.regitration.Repository.CustomerRepositery;

@RestController
public class CustREstController {

	@Autowired
	private CustomerRepositery customerRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authmanager;

	@PostMapping("/register")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {

		String encode = passwordEncoder.encode(customer.getPwd());
		customer.setPwd(encode);
		customerRepo.save(customer);
		return new ResponseEntity<>("Customer register", HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<String> LoginChek(@RequestBody
	  Customer customer){
		UsernamePasswordAuthenticationToken token= new
	  UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPwd());
		try {
	  org.springframework.security.core.Authentication Auth= authmanager.authenticate(token);
	  
	  if (Auth.isAuthenticated()) { 
		  return new ResponseEntity<>("WelCome TO Software IT Club",HttpStatus.OK);
	   }
		}catch(Exception e){
			e.printStackTrace();
		}
	  return new
	  ResponseEntity<>("invalid Cradintial",HttpStatus.BAD_REQUEST); }

	/*
	 * @PostMapping("/login") public ResponseEntity<String> LoginChek(@RequestBody
	 * Customer customer) { UsernamePasswordAuthenticationToken token = new
	 * UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPwd());
	 * 
	 * try { Authentication auth = (Authentication) authmanager.authenticate(token);
	 * 
	 * if (((org.springframework.security.core.Authentication)
	 * auth).isAuthenticated()) { return
	 * ResponseEntity.ok("Welcome to Software IT Club"); } } catch
	 * (BadCredentialsException e) { return new
	 * ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST); }
	 * 
	 * return new ResponseEntity<>("Invalid credentials", HttpStatus.BAD_REQUEST); }
	 * 
	 */

}
