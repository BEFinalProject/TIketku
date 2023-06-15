package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.HistoryTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryTransactionRepository extends JpaRepository<HistoryTransactionEntity, UUID> {
    @Query("SELECT h FROM HistoryTransactionEntity h WHERE h.uuid_users = :uuid_users")
    List<HistoryTransactionEntity> findByUUIDUsers(@Param("uuid_users")UUID uuid_users);

    @Query("SELECT h FROM HistoryTransactionEntity h WHERE h.departure_date = :departure_date AND h.uuid_users = :uuid_users")
    List<HistoryTransactionEntity> findByDepartureDate(@Param("departure_date") Date departure_date, @Param("uuid_users")UUID uuid_users);

    @Query("SELECT h FROM HistoryTransactionEntity h WHERE h.uuid_users = :uuid_users AND h.history_uid = :history_uid")
    List<HistoryTransactionEntity> findByUUIDUserAndHistory(@Param("uuid_users") UUID uuid_users, @Param("history_uid")UUID history_uid);
}