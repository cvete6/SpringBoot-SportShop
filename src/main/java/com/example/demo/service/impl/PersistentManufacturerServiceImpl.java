package com.example.demo.service.impl;

import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.models.Manufacturer;
import com.example.demo.repository.PersistentManufacturerRepository;
import com.example.demo.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersistentManufacturerServiceImpl implements ManufacturerService {

    private PersistentManufacturerRepository repo;

    public PersistentManufacturerServiceImpl(PersistentManufacturerRepository repo){
        this.repo=repo;
    }

    @Override
    public List<Manufacturer> getAll() {
        return repo.getAll();
    }

    public Manufacturer getManufacturerById(Long manufacturerId) {
        Optional<Manufacturer> m=repo.getById(manufacturerId);
        if(m.isPresent())
            return m.get();
        else throw new ManufacturerNotFoundException();
    }

    public void save(Manufacturer m){
        repo.save(m);
    }
}
