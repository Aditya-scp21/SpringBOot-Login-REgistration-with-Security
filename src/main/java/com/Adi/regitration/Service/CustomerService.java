package com.Adi.regitration.Service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Adi.regitration.Entity.Customer;
import com.Adi.regitration.Repository.CustomerRepositery;
@Service
public class CustomerService implements UserDetailsService {

	@Autowired
	private CustomerRepositery customerRepo;

	@Override
	public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
		Customer E = customerRepo.findByEmail(useremail);

		return new User(E.getEmail(), E.getPwd(), Collections.emptyList());
	}

}
