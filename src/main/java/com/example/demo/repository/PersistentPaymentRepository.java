package com.example.demo.repository;

import com.example.demo.models.ChargeRequest;
import com.example.demo.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PersistentPaymentRepository  extends Repository<ChargeRequest,Long> {

    ChargeRequest save( ChargeRequest cr);

   // get all charges
    @Query("select cr from ChargeRequest cr")
    List<ChargeRequest> getAll();

}
