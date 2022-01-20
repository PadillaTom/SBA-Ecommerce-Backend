package com.padillatom.ecommerce.repository;

import com.padillatom.ecommerce.model.enetity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
