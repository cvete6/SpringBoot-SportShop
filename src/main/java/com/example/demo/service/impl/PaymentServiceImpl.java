package com.example.demo.service.impl;

import com.example.demo.models.Accessory;
import com.example.demo.models.ChargeRequest;
import com.example.demo.models.Product;
import com.example.demo.repository.PersistentAccessoryRepository;
import com.example.demo.repository.PersistentPaymentRepository;
import com.example.demo.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PersistentPaymentRepository repo;

    public PaymentServiceImpl(PersistentPaymentRepository repo){
        this.repo=repo;
    }

    public void save(ChargeRequest cr){
        repo.save(cr);
    }


    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripePrivateKey;
    }

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);

    }
    @Override
    public List<ChargeRequest> getAllCharges() {
        return repo.getAll();
    }

    @Override
    public ChargeRequest addNewCharge (ChargeRequest chargeRequest)
    {
        repo.save(chargeRequest);
        return chargeRequest;
    }
}
