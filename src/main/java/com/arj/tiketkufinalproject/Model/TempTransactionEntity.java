package com.arj.tiketkufinalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "temp_transaction")
public class TempTransactionEntity {
    @Id
    private UUID transaction_uid;
    private UUID uuid_user;
    private UUID schedule_uid;
    private int seats_id;
    private String departure_city;
    private String arrival_city;
    private Date departure_date;
    private int total_passenger;
    private String seat_class;
    private String airplane_name;
    private String seat_number;
    private int id_price;
    private int price;
    private String title;
    private String full_name;
    private String given_name;
    private Date birth_date;
    private String id_card;
    private Date valid_until;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;

}
