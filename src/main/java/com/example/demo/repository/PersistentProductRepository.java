package com.example.demo.repository;

import com.example.demo.models.Category;
import com.example.demo.models.Manufacturer;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface PersistentProductRepository extends Repository<Product,Long> {

    Product save(Product p);

    //get all products
    @Query("select p from Product p")
    List<Product> getAll();

    //get product by id
    @Query("select p from Product p where p.id=:id")
    Optional<Product> getById(@Param("id") Long id);

    //get product by category
    @Query("select p from Product p where p.category=:category")
    List<Product> getByCategory(@Param("category") Category category);

    //get by category and manufacturer
    @Query("select p from Product p where p.category=:category and p.manufacturer=:manufacturer")
    List<Product> getByCategoryAndManufacturer(@Param("category") Category category, @Param("manufacturer") Manufacturer manufacturer);

    //price by category
    @Query(value="select sum(p.price) from Product p where p.category=:category")
    Double calculatePrice(@Param("category") Category category);

}
