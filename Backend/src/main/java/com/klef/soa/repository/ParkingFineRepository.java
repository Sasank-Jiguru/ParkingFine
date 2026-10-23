package com.klef.soa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.ParkingFine;

@Repository
public interface ParkingFineRepository
        extends JpaRepository<ParkingFine, Long> {
}
