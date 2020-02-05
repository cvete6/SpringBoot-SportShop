package com.example.demo.service;

import com.example.demo.models.ChargeRequest;
import com.example.demo.models.Product;
import com.stripe.exception.*;
import com.stripe.model.Charge;

import java.util.List;

public interface PaymentService {

    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException;
    List<ChargeRequest> getAllCharges();

    ChargeRequest addNewCharge (ChargeRequest chargeRequest);
}
