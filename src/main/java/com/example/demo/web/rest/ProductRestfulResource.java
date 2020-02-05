package com.example.demo.web.rest;

import com.example.demo.models.Category;
import com.example.demo.models.Manufacturer;
import com.example.demo.models.Product;
import com.example.demo.service.impl.PersistentCategoryServiceImpl;
import com.example.demo.service.impl.PersistentManufacturerServiceImpl;
import com.example.demo.service.impl.PersistentProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest/")
public class ProductRestfulResource {
    private PersistentProductServiceImpl productService;
    private PersistentCategoryServiceImpl categoryService;
    private PersistentManufacturerServiceImpl manufacturerService;

    public ProductRestfulResource(PersistentCategoryServiceImpl categoryService, PersistentManufacturerServiceImpl manufacturerService, PersistentProductServiceImpl productService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("products/{id}")
    public Product getById(@PathVariable("id") Long pathId){
        return productService.getById(pathId);
    }

    @GetMapping("product/category/{categoryId}")
    public List<Product> getByCategory(@PathVariable("categoryId") Long pathId){
        Category c=categoryService.getCategoryById(pathId);
        return productService.getByCategory(c);
    }

    @GetMapping("product/category/{categoryId}/manufacturer/{manufacturerId}")
    public List<Product> getByCategoryAndManufacturer(@PathVariable("categoryId") Long categoryId,
                                                      @PathVariable("manufacturerId") Long manufacturerId){
        Category c=categoryService.getCategoryById(categoryId);
        Manufacturer m=manufacturerService.getManufacturerById(manufacturerId);
        return productService.getByCategoryAndManufacturer(c,m);
    }

    @GetMapping("product/category/{categoryId}/price")
    public Double calculatePrice(@PathVariable("categoryId") Long categoryId){
        Category c=categoryService.getCategoryById(categoryId);
        return productService.calculatePrice(c);
    }
}
