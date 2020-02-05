package com.example.demo.service;

import com.example.demo.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAll();
    Manufacturer getManufacturerById(Long manufacturerId);
}
