package com.buildledger.backend.repository;

import com.buildledger.backend.model.ledger.accounting.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.building.id = :id")
    List<Expense> findAllByBuildingId(
            @Param("id") long id);
    @Query("SELECT e FROM Expense e WHERE e.building.id = :id AND e.date = :currentDate")
    List<Expense> findAllByBuildingIdAndDate(long id,@Param("currentDate") LocalDate currentDate);
}
