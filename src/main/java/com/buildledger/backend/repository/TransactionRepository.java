package com.buildledger.backend.repository;

import com.buildledger.backend.model.ledger.accounting.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.building.id = :id")
    List<Transaction> findByBuildingId(@Param("id") long id);
}
