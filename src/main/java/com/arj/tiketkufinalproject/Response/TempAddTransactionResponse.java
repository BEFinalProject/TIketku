package com.arj.tiketkufinalproject.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TempAddTransactionResponse {
    private UUID uuid_user;
    private UUID schedule_uid;
    private int seats_id;
    private int total_passenger;
    private String airplane_name;
    private int id_price;
}
