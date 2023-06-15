package com.arj.tiketkufinalproject.Scheduler;

import com.arj.tiketkufinalproject.Service.TempTransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@Slf4j
public class Schedule {
    @Autowired
    TempTransactionService transactionService;
    //digunakan untuk menghapus transaksi
    //@Scheduled(cron = "")
    @Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void Schedule() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String resetData = "00:00:00";
        //String date2String = "00:00:00";
        Time currentTime = new Time(System.currentTimeMillis());
        System.out.println(currentTime);

        try {
            java.util.Date reset = sdf.parse(resetData);
            java.util.Date reset2 = sdf.parse(String.valueOf(currentTime));
            java.util.Date current = sdf.parse(String.valueOf(currentTime));

            // Mengubah tipe data menjadi java.sql.Date
            Date resetDate = new Date(reset.getTime());
            Date resetDateFifteenMinutes = new Date(reset2.getTime() + (1 * 60 * 1000));
            Date currentDate = new Date(current.getTime());
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

            int result2 = resetDateFifteenMinutes.compareTo(currentDate);
            if (result2 < 0) {
                System.out.println("15 Minutes resetDate is before currentDate");
                boolean isBefore = resetDateFifteenMinutes.before(currentDate);
                System.out.println("Is resetDate before currentDate? " + isBefore);
            } else if (result2 > 0) {
                System.out.println("15 Minutes resetDate is after currentDate");
                boolean isAfter = resetDateFifteenMinutes.after(currentDate);
                System.out.println("Is resetDate after currentDate? " + isAfter);
            } else {
                System.out.println("15 Minutes resetDate is equal to currentDate");
                boolean isEqual = resetDateFifteenMinutes.equals(currentDate);
                System.out.println("Is resetDate equal to currentDate? " + isEqual);
                transactionService.deleteByStatusUnpaid();
            }


            // Membandingkan tanggal menggunakan operasi boolean

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
