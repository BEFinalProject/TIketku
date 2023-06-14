package com.arj.tiketkufinalproject.Controller;

import com.arj.tiketkufinalproject.Model.HistoryTransactionEntity;
import com.arj.tiketkufinalproject.Model.TempTransactionEntity;
import com.arj.tiketkufinalproject.Response.*;
import com.arj.tiketkufinalproject.Service.HistoryTransactionService;
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
    @Autowired
    HistoryTransactionService historyTransactionService;

    @PostMapping(value = "/addTempTransaction")
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

            HistoryTransactionEntity historyTransaction = new HistoryTransactionEntity();
            historyTransaction.setHistory_uid(transactionEntity.getTransaction_uid());
            log.info(String.valueOf(historyTransaction));
            HistoryTransactionEntity saveHistory = historyTransactionService.saveDataHistory(historyTransaction);

            return commonResponseGenerator.succsesResponse(saveHistory, "Sukses Menambahkan Data");
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

    @PutMapping(value = "/checkout")
    @Operation(description = "Update a temporary transaction to paid status")
    public CommonResponse<TempTransactionEntity> checkoutTransaction(@RequestBody CheckoutTransactionResponse param){
        try {
            TempTransactionEntity tempTransaction = new TempTransactionEntity();
            tempTransaction.setTransaction_uid(param.getTransaction_uid());
            tempTransaction.setTitle(param.getTitle());
            tempTransaction.setFull_name(param.getFull_name());
            tempTransaction.setGiven_name(param.getGiven_name());
            tempTransaction.setBirth_date(param.getBirth_date());
            tempTransaction.setId_card(param.getId_card());
            TempTransactionEntity tempTransactionEntity = tempTransactionService.updateTempData(tempTransaction);

            HistoryTransactionEntity historyTransaction = new HistoryTransactionEntity();
            historyTransaction.setHistory_uid(tempTransactionEntity.getTransaction_uid());
            log.info(String.valueOf(historyTransaction));
            HistoryTransactionEntity saveHistory = historyTransactionService.updateDataHistory(historyTransaction);

            log.info(String.valueOf(tempTransaction));
            log.info(String.valueOf(tempTransactionEntity), "Successfully updated " + param.getFull_name());
            return commonResponseGenerator.succsesResponse(tempTransactionEntity, "Successfully updated " + param.getFull_name());
        }catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/cancelCheckout")
    @Operation(description = "Update a temporary transaction to cancel status")
    public CommonResponse<TempTransactionEntity> cancelCheckout(@RequestBody CancelCheckoutResponse param){
        try {
            TempTransactionEntity tempTransaction = new TempTransactionEntity();
            tempTransaction.setTransaction_uid(param.getTransaction_uid());
            TempTransactionEntity tempTransactionEntity = tempTransactionService.updateTempData(tempTransaction);

            HistoryTransactionEntity historyTransaction = new HistoryTransactionEntity();
            historyTransaction.setHistory_uid(tempTransactionEntity.getTransaction_uid());
            log.info(String.valueOf(historyTransaction));
            HistoryTransactionEntity saveHistory = historyTransactionService.cancelOrder(historyTransaction);

            log.info(String.valueOf(tempTransaction));
            log.info(String.valueOf(tempTransactionEntity), "Successfully updated " + param.getTransaction_uid());
            return commonResponseGenerator.succsesResponse(tempTransactionEntity, "Successfully updated status to canceled");
        }catch (Exception e){
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
