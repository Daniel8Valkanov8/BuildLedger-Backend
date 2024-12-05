package com.buildledger.backend.repository;


import com.buildledger.backend.model.sos.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {
    @Query("SELECT g FROM Garage g WHERE g.cooperation.id = :cooperationID")
    List<Garage> getAllGaragesByCooperationID(@Param("cooperationID") long cooperationID);

    @Query("SELECT g FROM Garage g WHERE g.cooperation.id = :cooperationID AND g.sold = false")
    List<Garage> getAllFreeGaragesByCooperationID(@Param("cooperationID") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Garage g WHERE g.cooperation.id = :id")
    void deleteAllGaragesByCooperationId(@Param("id") Long id);

}
