package com.buildledger.backend.repository;
import com.buildledger.backend.model.ledger.Sell;
import com.buildledger.backend.model.persons.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellRepository extends JpaRepository<Sell, Long> {

    @Query("SELECT s FROM Sell s WHERE s.cooperationId = :id")

    List<Sell> findAllByCooperationId(@Param("id") Long id);

    @Query("SELECT COUNT(s) FROM Sell s WHERE s.purchaser = :oldPurchaser")
    int countByPurchaser(
            @Param("oldPurchaser")Purchaser oldPurchaser);
}
