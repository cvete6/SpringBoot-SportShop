package com.example.demo.web.rest;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.models.ChargeRequest;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class PaymentRestController {

    private PaymentService paymentService;

    private ProductService productService;

    public PaymentRestController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @GetMapping("/transactions")
    public List<ChargeRequest> getAllCharges(){
        return paymentService.getAllCharges();
    }


}
