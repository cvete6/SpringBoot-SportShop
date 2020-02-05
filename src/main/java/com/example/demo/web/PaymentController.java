package com.example.demo.web;

import com.example.demo.exceptions.CategoryNotFoundException;
import com.example.demo.exceptions.ManufacturerNotFoundException;
import com.example.demo.models.ChargeRequest;
import com.example.demo.models.Product;
import com.example.demo.service.PaymentService;
import com.example.demo.service.ProductService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private PaymentService paymentService;

    private ProductService productService;

    public PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @RequestMapping("/checkout/{id}")
    public String checkout(@PathVariable("id") Long id,
                           Model model) {
        Product product = productService.getById(id);
        model.addAttribute("name", product.getName());
        model.addAttribute("amount", product.getPrice()); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }

   @PostMapping("/charge")
    public String charge( ChargeRequest chargeRequest, Model model)
            throws StripeException {

        chargeRequest.setDescription("EMT payment");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }


    @ExceptionHandler({ManufacturerNotFoundException.class, CategoryNotFoundException.class})
    public String addNewCharge(@ModelAttribute ChargeRequest chargeRequest, Model model) {
        paymentService.addNewCharge(chargeRequest);
        return "redirect:/transactions";
    }


}
