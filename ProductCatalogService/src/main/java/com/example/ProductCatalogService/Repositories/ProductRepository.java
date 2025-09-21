package com.example.ProductCatalogService.Repositories;

import com.example.ProductCatalogService.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findProductByid(long id);

    Product save(Product product);

    List<Product> findProductByDescriptionContainingIgnoreCase(String str);

    List<Product> findAll();

    @Query("select p from Product as p where lower(p.description) like lower(concat('%', :str, '%'))")
    List<Product> getProductByWord(String str);

}
