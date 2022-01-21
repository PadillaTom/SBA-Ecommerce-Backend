package com.padillatom.ecommerce.repository;

import com.padillatom.ecommerce.model.enetity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "productCategory", path = "product-category")
@CrossOrigin( origins = {
        "http://localhost:4200",
        "https://sba-onlinestore.netlify.app/products"
})
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
