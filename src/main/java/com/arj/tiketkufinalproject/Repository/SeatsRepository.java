package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.SeatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SeatsRepository extends JpaRepository<SeatsEntity, Integer> {
//    @Query("SELECT s FROM SeatsEntity s WHERE s.airplane_name LIKE '%:airplane_name%'")
//    List<SeatsEntity> findAllSeatsByAirplaneName(@Param("airplane_name") String airplane_name);
//    @Query(value = "SELECT COUNT(s) FROM SeatsEntity s")
//    int getTotalRows();
//
//    Optional<SeatsEntity> findSeats(String seat_number);
}
