package com.arj.tiketkufinalproject.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "schedules")
public class SchedulesEntity {
    @Id
    private UUID schedule_uid;
    private Date departure_date;
    private Time departure_time;
    private int price;
    private String airplane_name;
    private String departure_city;
    private String arrival_city;
    private String departure_city_code;
    private String arrival_city_code;
    private String departure_airport;
    private String arrival_airport;
    private String departure_airport_code;
    private String arrival_airport_code;
    private Time flight_duration;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created_at;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime modified_at;



}
