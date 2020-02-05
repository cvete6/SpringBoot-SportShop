package com.example.demo.repository;

import com.example.demo.models.Manufacturer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersistentManufacturerRepository extends Repository<Manufacturer,Long> {
    Manufacturer save(Manufacturer m);

    //get all categories
    @Query("select m from Manufacturer m")
    List<Manufacturer> getAll();

    //get category by id
    @Query("select m from Manufacturer m  where m.id=:id")
    Optional<Manufacturer> getById(@Param("id") Long id);
}
