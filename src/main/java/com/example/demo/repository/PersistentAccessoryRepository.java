package com.example.demo.repository;

import com.example.demo.models.Accessory;
import org.springframework.data.repository.Repository;

public interface PersistentAccessoryRepository extends Repository<Accessory,Long> {

    Accessory save(Accessory a);
}
