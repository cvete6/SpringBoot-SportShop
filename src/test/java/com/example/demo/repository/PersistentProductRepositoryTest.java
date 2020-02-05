package com.example.demo.repository;

import com.example.demo.models.Category;
import com.example.demo.models.Manufacturer;
import com.example.demo.models.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersistentProductRepositoryTest {

    @Autowired
    private PersistentProductRepository repo;

    @Autowired
    private PersistentCategoryRepository categoryRepository;

    @Autowired
    private PersistentManufacturerRepository manufacturerRepository;


    @Before
    public void init() {
        Category c = new Category();
        c.setName("patiki");
        categoryRepository.save(c);

        Manufacturer m = new Manufacturer();
        m.setName("Nike");
        manufacturerRepository.save(m);

        Product p1 = new Product();
        p1.setName("AirMax");
        p1.setPrice(1000);
        p1.setCategory(c);
        p1.setManufacturer(m);
        repo.save(p1);

        Product p2 = new Product();
        p2.setName("Blabla");
        p2.setPrice(2000);
        p2.setCategory(c);
        p2.setManufacturer(m);
        repo.save(p2);
    }

    @Test
    public void getAll() {
        List<Product> deviceList = repo.getAll();
        Assert.assertEquals(2,deviceList.size());
    }


}
