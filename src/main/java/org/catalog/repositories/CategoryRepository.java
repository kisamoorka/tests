package org.catalog.repositories;


import org.catalog.domain.ProductCategory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * Created by Moorka on 09.06.2017.
 */
@RepositoryRestResource
public interface CategoryRepository extends CrudRepository<ProductCategory, Long> {
    //public ProductCategory findByName(String name);
}
