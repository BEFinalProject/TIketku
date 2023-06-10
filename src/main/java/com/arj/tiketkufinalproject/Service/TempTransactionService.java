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

//    public TempTransactionEntity addUsers1(TempTransactionEntity theTransaction) {
//
//        SchedulesEntity theSchedule = schedulesRepository.getReferenceById(theTransaction.getSchedule_uid());
//        SeatsEntity theSeat = seatsRepository.getReferenceById(theTransaction.getSeats_id());
//        AirplanesEntity theAirplane = airplaneRepository.getReferenceById(theTransaction.getAirplane_name());
//
//        TempTransactionEntity tempTransaction = tempTransactionRepository.findById(theTransaction.getTransaction_uid()).orElse(null);
//
//
//
//        return tempTransactionRepository.save(
//                new TempTransactionEntity(
//                        generateUUID(),
//                        theTransaction.getUuid_user(),
//                        theTransaction.getSchedule_uid(),
//                        theTransaction.getSeats_id(),
//                        theSchedule.getDeparture_city(),
//                        theSchedule.getArrival_city(),
//                        theTransaction.getDeparture_date(),
//                        theTransaction.getTotal_passenger(),
//                        theSeat.getSeat_type(),
//                        theAirplane.getAirplane_name(),
//                        theSeat.getSeat_number(),
//                        LocalDateTime.now()
//                        )
//        );
//    }

    public TempTransactionEntity addTransaction(TempTransactionEntity tempTransaction) {
//        Optional<SeatsEntity> seatExist = seatsRepository.findSeats(tempTransaction.getSeat_number());
//        Optional<AirplanesEntity> airplaneExist = airplaneRepository.findAirplanes(tempTransaction.getAirplane_name());
//        int maxCount = seatsRepository.getTotalRows();
//        if (seatExist.isPresent() && airplaneExist.isPresent()) {
//            throw new RuntimeException("Seat " +tempTransaction.getSeat_number()+ " dan Airplane  " + tempTransaction.getAirplane_name() + " Sudah ada");
//        }


        //TempTransactionEntity tempTransaction = new TempTransactionEntity();
//        UsersEntity user = new UsersEntity();
//        SchedulesEntity schedulesEntity = new SchedulesEntity();
//        SeatsEntity seatsEntity = new SeatsEntity();
//        CitiesEntity citiesEntity = new CitiesEntity();
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        tempTransaction.setTransaction_uid(generateUUID());
//        tempTransaction.setUuid_user(user.getUuid_user());
//        tempTransaction.setSchedule_uid(schedulesEntity.getSchedule_uid());
//        tempTransaction.setSeats_id(seatsEntity.getSeats_id());
//        tempTransaction.setDeparture_city(citiesEntity.getCity_name());
//        tempTransaction.setArrival_city(citiesEntity.getCity_name());
//        tempTransaction.setDeparture_date(schedulesEntity.getDeparture_date());
//        tempTransaction.setTotal_passenger(tempTransaction.getTotal_passenger());
//        tempTransaction.setSeat_class(seatsEntity.getSeat_type());
//        tempTransaction.setAirplane_name(tempTransaction.getAirplane_name());
//        tempTransaction.setSeat_number(tempTransaction.getSeat_number());
//        tempTransaction.setCreated_at(currentDateTime);
//
//        if (maxCount >= tempTransaction.getTotal_passenger() && !seatExist.isPresent() && !airplaneExist.isPresent()) {
//            return tempTransactionRepository.save(tempTransaction);
//        } else {
//            return null;
//        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        UsersEntity users = usersRepository.getReferenceById(tempTransaction.getUuid_user());
        SchedulesEntity schedules = schedulesRepository.getReferenceById(tempTransaction.getSchedule_uid());
        SeatsEntity seats = seatsRepository.getReferenceById(tempTransaction.getSeats_id());
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
