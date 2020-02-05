package com.example.demo.web;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.ChargeRequest;
import com.example.demo.models.Manufacturer;
import com.example.demo.models.Product;
import com.example.demo.repository.PersistentCategoryRepository;
import com.example.demo.repository.PersistentManufacturerRepository;
import com.example.demo.repository.PersistentProductRepository;
import com.example.demo.service.impl.PersistentCategoryServiceImpl;
import com.example.demo.service.impl.PersistentManufacturerServiceImpl;
import com.example.demo.service.impl.PersistentProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;


@Controller
@RequestMapping("/")
public class ProductController {

    private PersistentCategoryServiceImpl categoryService;
    private PersistentManufacturerServiceImpl manufacturerService;
    private PersistentProductServiceImpl productService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    public ProductController(PersistentCategoryServiceImpl categoryService, PersistentManufacturerServiceImpl manufacturerService,
                             PersistentProductServiceImpl productService){
        this.categoryService=categoryService;
        this.manufacturerService=manufacturerService;
        this.productService=productService;
    }


    @GetMapping("products")
    public String products(Model model){
        model.addAttribute("productList",productService.getAllProducts());
        model.addAttribute("manufacturerList",manufacturerService.getAll());
        model.addAttribute("categoryList",categoryService.getAll());
        return "products";
    }

    @GetMapping("products/add")
    public String addProduct(Model model){
        model.addAttribute("manufacturerList",manufacturerService.getAll());
        model.addAttribute("categoryList",categoryService.getAll());
        model.addAttribute("product", new Product()); //product se ispraka na products.add.html
        return "products.add";
    }

    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    @PostMapping("products/add")
    public String addDevice(@ModelAttribute Product product, Model model) {
        productService.addNewProduct(product);
        return "redirect:/products";
    }

    @GetMapping("product/{id}")
    public String productDetails(@PathVariable("id") String pathId, Model model){
        Product product=productService.getById(Long.parseLong(pathId));
        model.addAttribute("product",product);

        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);

        return "product.details";
    }
    @Autowired
    //za da se prezeme referenca od nesto sto vejce e injectirano da ja iskoristam istata referenca na obj
    private PersistentProductRepository repo;

    @Autowired
    private PersistentCategoryRepository categoryRepository;

    @Autowired
    private PersistentManufacturerRepository manufacturerRepository;
    @PostConstruct
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
}
