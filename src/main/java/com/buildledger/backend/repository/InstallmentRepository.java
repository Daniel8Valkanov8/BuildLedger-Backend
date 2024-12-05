package com.buildledger.backend.repository;

import com.buildledger.backend.model.ledger.Installment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

    @Query("SELECT i FROM Installment i WHERE i.payment.sell.cooperationId = :id")
    List<Installment> getAllInstallmentsByCooperationId(Long id);
}
