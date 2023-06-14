package com.arj.tiketkufinalproject.Service;

import com.arj.tiketkufinalproject.Model.HistoryTransactionEntity;
import com.arj.tiketkufinalproject.Model.TempTransactionEntity;
import com.arj.tiketkufinalproject.Model.UsersEntity;
import com.arj.tiketkufinalproject.Repository.HistoryTransactionRepository;
import com.arj.tiketkufinalproject.Repository.TempTransactionRepository;
import com.arj.tiketkufinalproject.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class HistoryTransactionService {
    @Autowired
    HistoryTransactionRepository historyTransactionRepository;
    @Autowired
    TempTransactionRepository tempTransactionRepository;
    @Autowired
    UsersRepository usersRepository;

    public List<HistoryTransactionEntity> searchHistoryUsers(UUID uuid_users){
        return historyTransactionRepository.findByUUIDUsers(uuid_users);
    }

    public UUID generateUUID() {
        return UUID.randomUUID();
    }

    /*public HistoryTransactionEntity saveDataHistory(HistoryTransactionEntity historyTransaction) {
        TempTransactionEntity transaction = tempTransactionRepository.getReferenceById(historyTransaction.getTransaction_uid());
        HistoryTransactionEntity historyData = new HistoryTransactionEntity();
        historyData.setHistory_transaction_uid(generateUUID());
        historyData.setTransaction_uid(transaction.getTransaction_uid());
        historyData.setUuid_users(transaction.getUuid_user());
        historyData.setDeparture_city(transaction.getDeparture_city());
        historyData.setArrival_city(transaction.getArrival_city());
        historyData.setDeparture_date(transaction.getDeparture_date());
        historyData.setSeat_class(transaction.getSeat_class());
        historyData.setAirplane_name(transaction.getAirplane_name());
        historyData.setSeat_number(transaction.getSeat_number());
        historyData.setStatues("null");

        return historyTransactionRepository.save(historyData);
    }*/

    public HistoryTransactionEntity saveDataHistory(HistoryTransactionEntity historyTransaction) {
        TempTransactionEntity transaction = tempTransactionRepository.getReferenceById(historyTransaction.getHistory_uid());
        TempTransactionEntity transactionExsist = tempTransactionRepository.findById(historyTransaction.getHistory_uid()).orElse(null);
        if (transactionExsist == null) {
            throw new RuntimeException("Transaksi tidak ditemukan");
        }

        HistoryTransactionEntity historyData = new HistoryTransactionEntity();
        historyData.setHistory_uid(transaction.getTransaction_uid());
        historyData.setUuid_users(transaction.getUuid_user());
        historyData.setDeparture_city(transaction.getDeparture_city());
        historyData.setArrival_city(transaction.getArrival_city());
        historyData.setDeparture_date(transaction.getDeparture_date());
        historyData.setSeat_class(transaction.getSeat_class());
        historyData.setAirplane_name(transaction.getAirplane_name());
        historyData.setSeat_number(transaction.getSeat_number());
        historyData.setStatues("Unpaid");

        return historyTransactionRepository.save(historyData);
    }

    public HistoryTransactionEntity updateDataHistory(HistoryTransactionEntity historyTransaction){
//        TempTransactionEntity updateTempTranscation = tempTransactionRepository.findById(historyTransaction.getTransaction_uid()).get();
//        updateTempTranscation.

        TempTransactionEntity transaction = tempTransactionRepository.getReferenceById(historyTransaction.getHistory_uid());
        HistoryTransactionEntity historyTransactionExists = historyTransactionRepository.findById(historyTransaction.getHistory_uid()).orElse(null);
        if (historyTransactionExists == null) {
            throw new RuntimeException("History Transaksi tidak ditemukan");
        }

        HistoryTransactionEntity historyData = new HistoryTransactionEntity();
        historyData.setHistory_uid(transaction.getTransaction_uid());
        historyData.setUuid_users(transaction.getUuid_user());
        historyData.setDeparture_city(transaction.getDeparture_city());
        historyData.setArrival_city(transaction.getArrival_city());
        historyData.setDeparture_date(transaction.getDeparture_date());
        historyData.setSeat_class(transaction.getSeat_class());
        historyData.setAirplane_name(transaction.getAirplane_name());
        historyData.setSeat_number(transaction.getSeat_number());
        historyData.setStatues("Paid");
        historyData.setTitle(transaction.getTitle());
        historyData.setFull_name(transaction.getFull_name());
        historyData.setGiven_name(transaction.getGiven_name());
        historyData.setBirth_date(transaction.getBirth_date());
        historyData.setId_card(transaction.getId_card());
        historyData.setValid_until(transaction.getDeparture_date());
        return historyTransactionRepository.save(historyData);
    }

    public HistoryTransactionEntity cancelOrder(HistoryTransactionEntity historyTransaction){
        TempTransactionEntity transaction = tempTransactionRepository.getReferenceById(historyTransaction.getHistory_uid());
        HistoryTransactionEntity historyTransactionExists = historyTransactionRepository.findById(historyTransaction.getHistory_uid()).orElse(null);
        if (historyTransactionExists == null) {
            throw new RuntimeException("History Transaksi tidak ditemukan");
        }

        HistoryTransactionEntity historyData = new HistoryTransactionEntity();
        historyData.setHistory_uid(transaction.getTransaction_uid());
        historyData.setUuid_users(transaction.getUuid_user());
        historyData.setDeparture_city(transaction.getDeparture_city());
        historyData.setArrival_city(transaction.getArrival_city());
        historyData.setDeparture_date(transaction.getDeparture_date());
        historyData.setSeat_class(transaction.getSeat_class());
        historyData.setAirplane_name(transaction.getAirplane_name());
        historyData.setSeat_number(transaction.getSeat_number());
        historyData.setStatues("Canceled");
        return historyTransactionRepository.save(historyData);
    }

    public HistoryTransactionEntity refundOrder(){
        HistoryTransactionEntity historyTransaction = new HistoryTransactionEntity();
        Date tanggal = historyTransaction.getDeparture_date();
        LocalDate currentDateTime = LocalDate.now();
        if(currentDateTime.isEqual(tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()) || currentDateTime.isAfter(tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())){

        }
        return null;
    }
}