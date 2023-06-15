package com.arj.tiketkufinalproject.Scheduler;

import com.arj.tiketkufinalproject.Model.TempTransactionEntity;
import com.arj.tiketkufinalproject.Service.TempTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

@Component
@Slf4j
public class Schedule {
    @Autowired
    TempTransactionService transactionService;
    //digunakan untuk menghapus transaksi
    //@Scheduled(cron = "")

//    private static final Time CURRENT_TIME;
//
//    static {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 1); // Tambah 15 menit
//        CURRENT_TIME = new Time(calendar.getTimeInMillis());
//    }
    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void Schedule() throws ParseException {
        //transactionService.searchCreatedAt();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String resetData = "00:00:00";

       // Date waktuCreate = new Date(temp.getCreated_at());

        //String date2String = "00:00:00";
        Time currentTime = new Time(System.currentTimeMillis());
        System.out.println(currentTime);

        try {
            TempTransactionEntity temp = new TempTransactionEntity();
            LocalDateTime createdAt = temp.getCreated_at();
            log.info(String.valueOf(createdAt));
            java.util.Date reset = sdf.parse(resetData);
            java.util.Date current = sdf.parse(String.valueOf(currentTime));
            if (createdAt != null) {
                LocalDateTime modifiedTime = createdAt.plusMinutes(1);
                Date createTime = (Date) Date.from(modifiedTime.atZone(ZoneId.systemDefault()).toInstant());
                Date currentCreatedAt = new Date(createTime.getTime());

            // Mengubah tipe data menjadi java.sql.Date
            Date resetDate = new Date(reset.getTime());
            Date currentDate = new Date(current.getTime());
            //Date currentCreatedAt = new Date(createTime.getTime());
            //date 1 = reset
            //date 2 = current

            // Membandingkan tanggal menggunakan metode compareTo()
            /*int result = resetDate.compareTo(currentDate);
            if (result < 0) {
                System.out.println("resetDate is before currentDate");
                boolean isBefore = resetDate.before(currentDate);
                System.out.println("Is resetDate before currentDate? " + isBefore);

            } else if (result > 0) {
                System.out.println("resetDate is after currentDate");
                boolean isAfter = resetDate.after(currentDate);
                System.out.println("Is resetDate after currentDate? " + isAfter);

            } else {
                System.out.println("resetDate is equal to currentDate");
                boolean isEqual = resetDate.equals(currentDate);
                System.out.println("Is resetDate equal to currentDate? " + isEqual);
                transactionService.truncate();
            }*/

            System.out.println("Current time freeze : "+ currentCreatedAt);
            int result2 = currentCreatedAt.compareTo(currentDate);
            if (result2 < 0) {
                System.out.println("15 Minutes resetDate is before currentDate");
                boolean isBefore = currentCreatedAt.before(currentDate);
                System.out.println("Is resetDate before currentDate? " + isBefore);
            } else if (result2 > 0) {
                System.out.println("15 Minutes resetDate is after currentDate");
                boolean isAfter = currentCreatedAt.after(currentDate);
                System.out.println("Is resetDate after currentDate? " + isAfter);
            } else {
                System.out.println("15 Minutes resetDate is equal to currentDate");
                boolean isEqual = currentCreatedAt.equals(currentDate);
                System.out.println("Is resetDate equal to currentDate? " + isEqual);
                transactionService.deleteByStatusUnpaid();
            }
            }
            else {
                System.out.println("created_at is null");
            }


            // Membandingkan tanggal menggunakan operasi boolean

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
