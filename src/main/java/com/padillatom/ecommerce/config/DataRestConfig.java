package com.padillatom.ecommerce.config;


import com.padillatom.ecommerce.model.enetity.Product;
import com.padillatom.ecommerce.model.enetity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    public DataRestConfig (@Autowired @Lazy EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // CORS:
        cors.addMapping("/*").allowedOrigins("*").allowedMethods("GET").allowCredentials(false);

        // To Be Disabled:
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};

        // Product
        config.getExposureConfiguration()
                .forDomainType(Product.class)
                .withItemExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions));

        // Product Category
        config.getExposureConfiguration()
                .forDomainType(ProductCategory.class)
                .withItemExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions));

        // Internal Helper to modify JSON generated by Rest Data:
        exposeIds(config);
    }

    private void exposeIds(RepositoryRestConfiguration config){
        // Get Entities:
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        // Save them in Array:
        List<Class> entityClasses = new ArrayList<>();
        for(EntityType ent : entities) {
            entityClasses.add(ent.getJavaType());
        }
        // === Expose IDs ===
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }

}
