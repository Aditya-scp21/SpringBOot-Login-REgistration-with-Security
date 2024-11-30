package com.Adi.regitration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Adi.regitration.Entity.Customer;

public interface CustomerRepositery extends JpaRepository<Customer, Long> {
	public Customer findByEmail(String Email);
}
