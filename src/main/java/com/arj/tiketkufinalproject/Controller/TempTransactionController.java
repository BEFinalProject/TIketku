package com.arj.tiketkufinalproject.Controller;

import com.arj.tiketkufinalproject.Model.TempTransactionEntity;
import com.arj.tiketkufinalproject.Response.CommonResponse;
import com.arj.tiketkufinalproject.Response.CommonResponseGenerator;
import com.arj.tiketkufinalproject.Response.TempAddTransactionResponse;
import com.arj.tiketkufinalproject.Service.TempTransactionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value ="/TempTransacation")
@Api(value = "TempTransacation")
public class TempTransactionController {

    @Autowired
    TempTransactionService tempTransactionService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @PostMapping(value = "/addTempTamperedTransaction")
    @Operation(description = "")
    public CommonResponse<TempTransactionEntity> addTransaction(@RequestBody TempAddTransactionResponse param) {
        try {
            TempTransactionEntity tempTransaction = new TempTransactionEntity();
            tempTransaction.setUuid_user(param.getUuid_user());
            tempTransaction.setSchedule_uid(param.getSchedule_uid());
            tempTransaction.setSeats_id(param.getSeats_id());
            tempTransaction.setTotal_passenger(param.getTotal_passenger());
            tempTransaction.setAirplane_name(param.getAirplane_name());
            tempTransaction.setId_price(param.getId_price());
            log.info(String.valueOf(param));
            TempTransactionEntity transactionEntity =tempTransactionService.addTransaction(tempTransaction);
            return commonResponseGenerator.succsesResponse(transactionEntity, "Sukses Menambahkan Data");
        } catch (Exception e) {
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }


    @GetMapping(value = "/Search/{departure_city}/{arrival_city}/{departure_date}/{total_passanger}/{seat_type}")
    @Operation(description = "")
    public CommonResponse<List<TempTransactionEntity>> Search(
            @PathVariable String departure_city, @PathVariable String arrival_city,
            @PathVariable Date departure_date, @PathVariable int total_passanger, @PathVariable String seat_type){
        try {
            log.info(departure_city,arrival_city,total_passanger,seat_type,departure_date);
            List<TempTransactionEntity> tempTransactionEntities = tempTransactionService.search(departure_city,arrival_city,departure_date,total_passanger,seat_type);
            log.info(String.valueOf(tempTransactionEntities));
            return commonResponseGenerator.succsesResponse(tempTransactionEntities,"Sukses Mencari Jadwal Transaction");
        } catch (Exception e) {
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

//    @GetMapping(value = "/Search/{departure_city}/{arrival_city}/{departure_date}")
//    @Operation(description = "")
//    public CommonResponse<List<TempTransactionEntity>> Search(
//            @PathVariable String departure_city, @PathVariable String arrival_city, @PathVariable Date departure_date){
//        try {
//            log.info(departure_city);
//            List<TempTransactionEntity> tempTransactionEntities = tempTransactionService.search(departure_city,arrival_city, departure_date);
//            log.info(String.valueOf(tempTransactionEntities));
//            return commonResponseGenerator.succsesResponse(tempTransactionEntities,"Sukses Mencari Jadwal Transaction");
//        } catch (Exception e) {
//            log.warn(String.valueOf(e));
//            return commonResponseGenerator.failedResponse(e.getMessage());
//        }
//    }
}
