package com.arj.tiketkufinalproject.Repository;

import com.arj.tiketkufinalproject.Model.HistoryTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryTransactionRepository extends JpaRepository<HistoryTransactionEntity, UUID> {
    @Query("SELECT h FROM HistoryTransactionEntity h WHERE h.uuid_users = :uuid_users")
    List<HistoryTransactionEntity> findByUUIDUsers(@Param("uuid_users")UUID uuid_users);
}