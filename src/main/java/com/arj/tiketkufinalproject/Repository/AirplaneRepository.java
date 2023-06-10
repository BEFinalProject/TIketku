package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.AirplanesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirplaneRepository extends JpaRepository<AirplanesEntity, String> {
//    Optional<AirplanesEntity> findAirplanes(String airplane_name);
}
