package com.arj.tiketkufinalproject.Service;

import com.arj.tiketkufinalproject.Model.CitiesEntity;
import com.arj.tiketkufinalproject.Repository.CitiesRepository;
import com.arj.tiketkufinalproject.Response.CitiesRequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitiesService {
    @Autowired
    CitiesRepository citiesRepository;
    private LocalDateTime currentDateTime = LocalDateTime.now();


    public CitiesEntity getByCityDepartureAndArrival(String departure_city, String arrival_city) {
        if(departure_city == arrival_city){
            throw new RuntimeException("Departure and arrival city cannot be same");
        }
        return citiesRepository.getByCityDepartureAndArrival(departure_city, arrival_city);
    }

//    public List<CitiesEntity> getByCityName(String city_name) {
//        return citiesRepository.findByName(city_name);
//    }

    public CitiesEntity addCity(CitiesEntity param){
        Optional<CitiesEntity> cityExsist = citiesRepository.findById(param.getCity_code());
        if(cityExsist.isPresent()){
            throw new RuntimeException("Film Code " + param.getCity_code() + " Sudah Ada");
        }
        param.setCreated_at(currentDateTime);
        return citiesRepository.save(param);
    }

    public List<CitiesEntity> addMultipleCity(List<CitiesEntity> param){
        List<CitiesEntity> list = new ArrayList<>();
        for (CitiesEntity city : param) {
            Optional<CitiesEntity> citiesExist = citiesRepository.findById(city.getCity_code());
            if(citiesExist.isPresent()) {
                throw new RuntimeException("Cities already " + city.getCity_code() + " exist");
            }else {
                list.add(citiesRepository.save(city));
            }
        }
        return list;
    }

    public CitiesEntity updateCity(CitiesEntity param){
        CitiesEntity updateCity = citiesRepository.findById(param.getCity_code()).get();
        updateCity.setCity_name(param.getCity_name());
        updateCity.setModified_at(currentDateTime);
        return citiesRepository.save(updateCity);
    }

    public CitiesEntity deleteCity(String param){
        CitiesEntity delCity = citiesRepository.findById(param).get();
        citiesRepository.deleteById(param);
        return delCity;
    }
}
