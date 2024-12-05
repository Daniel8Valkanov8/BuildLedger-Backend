package com.buildledger.backend.repository;

import com.buildledger.backend.model.sos.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
    @Modifying
    @Transactional
    @Query("delete from Floor f where f.cooperation.id = :id")
    void deleteAllByCooperationId(@Param("id") Long id);
}
