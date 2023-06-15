package com.arj.tiketkufinalproject.Service;

import com.arj.tiketkufinalproject.Model.*;
import com.arj.tiketkufinalproject.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TempTransactionService {
    @Autowired
    TempTransactionRepository tempTransactionRepository;
    @Autowired
    SchedulesRepository schedulesRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    SeatsRepository seatsRepository;
    @Autowired
    AirplaneRepository airplaneRepository;
    @Autowired
    PriceRepository priceRepository;

    public TempTransactionEntity addTransaction(TempTransactionEntity tempTransaction) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        UsersEntity users = usersRepository.getReferenceById(tempTransaction.getUuid_user());
        SeatsEntity seats = seatsRepository.getReferenceById(tempTransaction.getSeats_id());
        SchedulesEntity schedules = schedulesRepository.getReferenceById(tempTransaction.getSchedule_uid());
        AirplanesEntity airplanes = airplaneRepository.getReferenceById(tempTransaction.getAirplane_name());
        PriceEntity price = priceRepository.getReferenceById(tempTransaction.getId_price());
//        SeatsEntity seatExsist = seatsRepository.getByStudioSeat(transaction.getStudio_name(), transaction.getNomor_kursi());


        TempTransactionEntity existingTransaction = tempTransactionRepository.findBySeatNumberAndAirplane(tempTransaction.getSeats_id(), tempTransaction.getAirplane_name());
        if (existingTransaction != null) {
            throw new RuntimeException("Seat number and Airplane u chose already exist. Cannot save the transaction.");
        }

        TempTransactionEntity transactionExist = new TempTransactionEntity();
        transactionExist.setTransaction_uid(generateUUID()); // Menetapkan UUID baru
        transactionExist.setUuid_user(users.getUuid_user());
        transactionExist.setSchedule_uid(schedules.getSchedule_uid());
        transactionExist.setSeats_id(seats.getSeats_id());
        transactionExist.setDeparture_city(schedules.getDeparture_city());
        transactionExist.setArrival_city(schedules.getArrival_city());
        transactionExist.setDeparture_date(schedules.getDeparture_date());
        transactionExist.setTotal_passenger(tempTransaction.getTotal_passenger());
        transactionExist.setSeat_class(seats.getSeat_type());
        transactionExist.setAirplane_name(airplanes.getAirplane_name());
        transactionExist.setSeat_number(seats.getSeat_number());
        transactionExist.setCreated_at(currentDateTime);
        transactionExist.setId_price(price.getId_price());
        transactionExist.setPrice(price.getPrice());

        return tempTransactionRepository.save(transactionExist);


    }

    public TempTransactionEntity updateTempData(TempTransactionEntity tempTransaction){
        TempTransactionEntity updatedTempData = tempTransactionRepository.findById(tempTransaction.getTransaction_uid()).get();
        updatedTempData.setTitle(tempTransaction.getTitle());
        updatedTempData.setFull_name(tempTransaction.getFull_name());
        updatedTempData.setGiven_name(tempTransaction.getGiven_name());
        updatedTempData.setBirth_date(tempTransaction.getBirth_date());
        updatedTempData.setId_card(tempTransaction.getId_card());
        updatedTempData.setValid_until(tempTransaction.getDeparture_date());
        return tempTransactionRepository.save(updatedTempData);
    }

    public TempTransactionEntity cancelOrder(TempTransactionEntity tempTransaction){
        TempTransactionEntity updatedTempData = tempTransactionRepository.findById(tempTransaction.getTransaction_uid()).get();
        return tempTransactionRepository.save(updatedTempData);
    }

    public TempTransactionEntity refundOrder(TempTransactionEntity tempTransaction){

        return null;
    }

    public void truncate(){
        tempTransactionRepository.deleteAll();
    }

    public void deleteByStatusUnpaid(){
        tempTransactionRepository.deleteByStatusUnpaid();
    }

    public List<TempTransactionEntity> search(
            String departure_city, String arrival_city, Date departure_date,
            int total_passanger,String seat_type){
        return tempTransactionRepository.searching(departure_city, arrival_city,departure_date, total_passanger,seat_type);
    }

//    public List<TempTransactionEntity> search(
//            String departure_city, String arrival_city, Date departure_date){
//        return tempTransactionRepository.searching(departure_city,arrival_city, departure_date);
//    }
    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
