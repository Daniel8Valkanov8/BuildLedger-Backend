package com.buildledger.backend.repository;

import com.buildledger.backend.model.ledger.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>
{

    @Query("SELECT p FROM Payment p WHERE p.sell.cooperationId = :id")
    List<Payment> getAllPaymentsByCooperationId(@Param("id") Long id);
}
