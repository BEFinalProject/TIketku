package com.arj.tiketkufinalproject.Controller;

import com.arj.tiketkufinalproject.Model.HistoryTransactionEntity;
import com.arj.tiketkufinalproject.Response.CommonResponse;
import com.arj.tiketkufinalproject.Response.CommonResponseGenerator;
import com.arj.tiketkufinalproject.Service.HistoryTransactionService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value ="/HistoryTransaction")
@Api(value = "HistoryTransaction")
public class HistoryTransactionController {
    @Autowired
    HistoryTransactionService historyTransactionService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @GetMapping(value = "/{uuid_users}")
    @Operation(description = "Menampilkan Users Transaksi")
    public CommonResponse<List<HistoryTransactionEntity>> getHistory(@PathVariable UUID uuid_users){
        try {
            List<HistoryTransactionEntity> historyTransaction = historyTransactionService.searchHistoryUsers(uuid_users);
            log.info(String.valueOf(historyTransaction));
            return commonResponseGenerator.succsesResponse(historyTransaction,"Sukses Mencari Jadwal Transaction");
        } catch (Exception e) {
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }
}