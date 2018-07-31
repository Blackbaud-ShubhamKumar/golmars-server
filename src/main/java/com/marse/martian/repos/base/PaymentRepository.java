package com.marse.martian.repos.base;

import org.springframework.stereotype.Repository;

import com.marse.martian.entities.Payment;

@Repository
public interface PaymentRepository extends BaseRepository<Payment, Long> {

}
