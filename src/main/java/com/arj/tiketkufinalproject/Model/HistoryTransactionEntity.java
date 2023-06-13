package com.arj.tiketkufinalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "history_transaction")
public class HistoryTransactionEntity {
    @Id
    private UUID history_transaction_uid;
    private UUID transaction_uid;
    private UUID uuid_users;
    private String departure_city;
    private String arrival_city;
    private Date departure_date;
    private String seat_class;
    private String airplane_name;
    private String seat_number;
    private String statues;
}