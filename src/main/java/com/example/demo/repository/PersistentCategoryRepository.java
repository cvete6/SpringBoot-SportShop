package com.example.demo.repository;

import com.example.demo.models.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersistentCategoryRepository extends Repository<Category,Long> {

    Category save(Category c);

    //get all categories
    @Query("select c from Category c")
    List<Category> getAll();

    //get category by id
    @Query("select c from Category c  where c.id=:id")
    Optional<Category> getById(@Param("id") Long id);

}
