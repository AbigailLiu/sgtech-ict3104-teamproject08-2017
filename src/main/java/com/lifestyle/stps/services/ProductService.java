package com.lifestyle.stps.services;

import com.lifestyle.stps.entities.Product;
import org.springframework.stereotype.Service;

/**
 * Created by User 1 on 25/9/2017.
 */
@Service
public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
