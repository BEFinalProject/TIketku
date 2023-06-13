package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.AirplanesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<AirplanesEntity, String> {
//    Optional<AirplanesEntity> findAirplanes(String airplane_name);
}
