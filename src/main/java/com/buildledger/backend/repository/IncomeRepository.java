package com.buildledger.backend.repository;

import com.buildledger.backend.model.ledger.accounting.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT i FROM Income i WHERE i.building.id = :cooperationId")
    List<Income> findByCooperationId(Long cooperationId);


    @Query("SELECT i FROM Income i WHERE i.building.id = :id AND i.date = :currentDate")
    List<Income> findByCooperationIdAndDate(long id,@Param("currentDate") LocalDate currentDate);
}
