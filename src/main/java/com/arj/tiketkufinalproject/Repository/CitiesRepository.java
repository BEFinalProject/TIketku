package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.CitiesEntity;
import com.arj.tiketkufinalproject.Response.CitiesRequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitiesRepository extends JpaRepository<CitiesEntity, String> {
    /*@Query("SELECT arrival_city.city_name AS arrival_city, departure_city.city_name AS departure_city" +
            "FROM cities AS arrival_city, cities AS departure_city" +
            "WHERE arrival_city.city_code = 'JKT'" +
            "  AND departure_city.city_code = 'SMG'")*/

    @Query("SELECT arrival.city_name AS arrival_city, departure.city_name AS departure_city " +
            "FROM CitiesEntity arrival, CitiesEntity departure " +
            "WHERE arrival.city_code = :arrival_city " +
            "AND departure.city_code = :departure_city")
    CitiesEntity getByCityDepartureAndArrival(@Param("departure_city") String departure_city, @Param("arrival_city") String arrival_city);

//    @Query("SELECT s FROM CitiesEntity s where s.city_name = :city_name")
//    CitiesEntity getByCityDepartureAndArrival(@Param("city_name") String city_name);

//    @Query("SELECT s FROM CitiesEntity s WHERE s.city_name = :city_name ")
//    List<CitiesEntity> findByName(@Param("city_name") String city_name);
}
