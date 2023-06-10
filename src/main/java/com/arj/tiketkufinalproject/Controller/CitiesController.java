package com.arj.tiketkufinalproject.Controller;

import com.arj.tiketkufinalproject.Model.CitiesEntity;
import com.arj.tiketkufinalproject.Model.UsersEntity;
//import com.arj.tiketkufinalproject.Response.CitiesRequestResponse;
import com.arj.tiketkufinalproject.Response.CitiesRequestResponse;
import com.arj.tiketkufinalproject.Response.CommonResponse;
import com.arj.tiketkufinalproject.Response.CommonResponseGenerator;
import com.arj.tiketkufinalproject.Service.CitiesService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/City")
@Api("City")
@Slf4j
public class CitiesController {
    @Autowired
    CitiesService citiesService;

    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @GetMapping("/findCityTicket")
    @Operation(description = "Menampilkan data ticket sesuai inputan departure dan arrival city")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CommonResponse<CitiesEntity> getByDepartureAndArrivalCity(@RequestParam String departure_city,
                                                                     @RequestBody String arrival_city){
        try {
            CitiesEntity city = citiesService.getByCityDepartureAndArrival(departure_city, arrival_city);
            log.info(String.valueOf(city), "Sukses Tampil Data Departure dan Arrival City");
            return commonResponseGenerator.succsesResponse(city,"Sukses Tampil Data Departure dan Arrival City") ;
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

//    @GetMapping(value = "/findByCityName/{city_name}")
//    @Operation(description = "Menampilkan City Berdasarkan Pencarian Nama")
//    public CommonResponse<List<CitiesEntity>> getByFilm_name(@PathVariable CitiesEntity request){
//        try {
//            List<CitiesEntity> cities = citiesService.getByCityName(request.getCity_name());
//            log.info(String.valueOf(cities), "Sukses Mencari Data '" + request.getCity_name() + "'");
//            return commonResponseGenerator.succsesResponse(cities, "Sukses Mencari Data '" + request.getCity_name() + "'");
//        } catch (Exception e) {
//            log.warn(String.valueOf(e));
//            return commonResponseGenerator.failedResponse(e.getMessage());
//        }
//    }


    @PostMapping("/addCity")
    @Operation(description = "Menambahkan City Ke Database")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CommonResponse<CitiesEntity> addCities(@RequestBody CitiesEntity param){
        try {
            CitiesEntity city = citiesService.addCity(param);
            log.info(String.valueOf(city), "Sukses Add Data City " + city.getCity_code());
            return commonResponseGenerator.succsesResponse(city,"Sukses Add Data City " + city.getCity_code()) ;
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @PostMapping(value = "/addMultipleCity")
    @Operation(description = "Menambahkan Banyak City Sekaligus Ke Database")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CommonResponse<List<CitiesEntity>> addMultipleCity(@RequestBody List<CitiesEntity> param){
        try {
            List<CitiesEntity> city = citiesService.addMultipleCity(param);
            log.info("Sukses Add Data " + city);
            return commonResponseGenerator.succsesResponse(city,"Sukses Add Data " + city) ;
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @PutMapping(value = "/updateCity")
    @Operation(description = "Mengupdate City Tertentu Dari Database")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CommonResponse<CitiesEntity> updateCity(@RequestBody CitiesEntity param){

        try {
            CitiesEntity city = citiesService.updateCity(param);
            log.info(String.valueOf(city),"Sukses Update Data " +city.getCity_code());
            return commonResponseGenerator.succsesResponse(city,"Sukses Update Data " +city.getCity_code());
        }
        catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @DeleteMapping(value = "deleteCity/{city_code}")
    @Operation(description = "Menghapus City Tertentu Dari Database Berdasarkan City Code")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CommonResponse<CitiesEntity> delFilm(@PathVariable String city_code){
        try {
            CitiesEntity city = citiesService.deleteCity(city_code);
            log.info(String.valueOf(city), "Sukses Menghapus Data " + city.getCity_code());
            return commonResponseGenerator.succsesResponse(city, "Sukses Menghapus Data " + city.getCity_code());
        }
        catch (EmptyResultDataAccessException e) {
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "City not found", e);
        }
        catch (Exception e) {
            log.error(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete City", e);
        }
    }
}
