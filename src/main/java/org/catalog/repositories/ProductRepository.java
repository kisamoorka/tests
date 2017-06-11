package org.catalog.repositories;

import org.catalog.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Moorka on 09.06.2017.
 */
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long>{

}
