package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.AirportsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportsRepository extends JpaRepository<AirportsEntity, String> {
//    Optional<AirportsEntity> findByIataCode(String iata_code);
//    Optional<AirportsEntity> findByAirportName(String airport_name);
}
