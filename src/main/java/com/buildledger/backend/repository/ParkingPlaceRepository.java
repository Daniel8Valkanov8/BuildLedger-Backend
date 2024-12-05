package com.buildledger.backend.repository;

import com.buildledger.backend.model.sos.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Long> {

    @Query("SELECT p FROM ParkingPlace p WHERE p.cooperation.id = :cooperationID")
    List<ParkingPlace> getAllParkingPlacesByCooperationID(@Param("cooperationID") long cooperationID);


    @Query("SELECT p FROM ParkingPlace p WHERE p.cooperation.id = :cooperationID AND p.sold = false")
    List<ParkingPlace> getAllFreeParkingPlacesByCooperationID(@Param("cooperationID") long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM ParkingPlace p WHERE p.cooperation.id = :id")
    void deleteAllByCooperationId(@Param("id") Long id);
}
