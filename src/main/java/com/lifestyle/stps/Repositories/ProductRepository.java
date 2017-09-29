package com.lifestyle.stps.Repositories;


import com.lifestyle.stps.entities.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by User 1 on 20/9/2017.
 */
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
