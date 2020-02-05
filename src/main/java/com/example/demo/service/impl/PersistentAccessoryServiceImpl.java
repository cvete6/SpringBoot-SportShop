package com.example.demo.service.impl;

import com.example.demo.models.Accessory;
import com.example.demo.repository.PersistentAccessoryRepository;

public class PersistentAccessoryServiceImpl {
    private PersistentAccessoryRepository repo;

    public PersistentAccessoryServiceImpl(PersistentAccessoryRepository repo){
        this.repo=repo;
    }

    public void save(Accessory a){
        repo.save(a);
    }
}
