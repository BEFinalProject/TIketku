package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.AirplanesEntity;
import com.arj.tiketkufinalproject.Model.SeatsEntity;
import com.arj.tiketkufinalproject.Model.TempTransactionEntity;
import com.arj.tiketkufinalproject.Model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TempTransactionRepository extends JpaRepository<TempTransactionEntity, UUID> {


    @Query("SELECT t FROM TempTransactionEntity t WHERE t.departure_city = :departure_city AND t.arrival_city = :arrival_city AND t.departure_date = :departure_date AND t.total_passenger = :total_passenger AND t.seat_class = :seat_class")
    List<TempTransactionEntity> searching(@Param("departure_city") String departure_city,
                                          @Param("arrival_city") String arrival_city,
                                          @Param("departure_date") Date departure_date,
                                          @Param("total_passenger") int total_passenger,
                                          @Param("seat_class") String seat_class);

    @Query("SELECT s FROM TempTransactionEntity s WHERE s.seats_id = :seats_id AND s.airplane_name = :airplane_name")
    TempTransactionEntity findBySeatNumberAndAirplane(int seats_id, String airplane_name);

    /*@Query("DELETE FROM temp_transaction t\n" +
            "USING history_transaction h\n" +
            "WHERE t.transaction_uid = h.history_uid\n" +
            "  AND h.statues = 'Unpaid';")
    TempTransactionEntity deleteByStatus();*/

    @Query("DELETE FROM TempTransactionEntity t WHERE t.transaction_uid IN (SELECT t.transaction_uid FROM TempTransactionEntity t INNER JOIN HistoryTransactionEntity h ON t.transaction_uid = h.history_uid WHERE h.statues = 'Unpaid')")
    TempTransactionEntity deleteByStatusUnpaid();

    @Query("SELECT t.created_at From TempTransactionEntity t")
    TempTransactionEntity selectCreatedat();

//    @Query("SELECT t from TempTransactionEntity t where t.departure_city = :departure_city AND t.arrival_city = :arrival_city AND t.departure_date = :departure_date")
//    List<TempTransactionEntity> searching(@Param("departure_city") String departure_city, @Param("arrival_city") String arrival_city, @Param("departure_date") Date departure_date);


}
