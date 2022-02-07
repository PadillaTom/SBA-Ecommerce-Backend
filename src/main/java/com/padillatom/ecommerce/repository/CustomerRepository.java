package com.padillatom.ecommerce.repository;

import com.padillatom.ecommerce.model.enetity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
