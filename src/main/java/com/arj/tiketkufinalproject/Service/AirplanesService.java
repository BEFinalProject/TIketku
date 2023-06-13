package com.arj.tiketkufinalproject.Service;

import com.arj.tiketkufinalproject.Model.AirplanesEntity;
import com.arj.tiketkufinalproject.Repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AirplanesService {
    @Autowired
    AirplaneRepository airplaneRepository;
    private LocalDateTime currentDateTime = LocalDateTime.now();

    public AirplanesEntity addAirplane(AirplanesEntity param){
        Optional<AirplanesEntity> airplaneExists = airplaneRepository.findById(param.getAirplane_name());
        if (airplaneExists.isPresent()) {
            throw new RuntimeException("Airplane name " + param.getAirplane_name() + " already exists");
        }
        param.setCreated_at(currentDateTime);
        return airplaneRepository.save(param);
    }

    public AirplanesEntity updateAirplane(AirplanesEntity param) {
        AirplanesEntity updateAirplane = airplaneRepository.findById(param.getAirplane_name()).get();
        updateAirplane.setAirplane_type(param.getAirplane_type());
        updateAirplane.setModified_at(currentDateTime);
        return airplaneRepository.save(updateAirplane);
    }

    public AirplanesEntity deleteAirplane(String param) {
        AirplanesEntity deleteAirplane = airplaneRepository.findById(param).get();
        airplaneRepository.deleteById(param);
        return deleteAirplane;
    }
}
