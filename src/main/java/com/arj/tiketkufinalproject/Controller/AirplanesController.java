package com.arj.tiketkufinalproject.Controller;

import com.arj.tiketkufinalproject.Model.AirplanesEntity;
import com.arj.tiketkufinalproject.Response.CommonResponse;
import com.arj.tiketkufinalproject.Response.CommonResponseGenerator;
import com.arj.tiketkufinalproject.Service.AirplanesService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "Airplane")
@Api("Airplane")
@Slf4j
public class AirplanesController {
    @Autowired
    AirplanesService airplanesService;
    @Autowired
    CommonResponseGenerator commonResponseGenerator;

    @PostMapping("/addAirplane")
    @Operation(description = "Adds a new Airplane to database")
    public CommonResponse<AirplanesEntity> addAirports(@RequestBody AirplanesEntity param){
        try {
            AirplanesEntity airplaneEntity = airplanesService.addAirplane(param);
            log.info(String.valueOf(airplaneEntity), "Successfully added " + param.getAirplane_name());
            return commonResponseGenerator.succsesResponse(airplaneEntity, "Successfully added " + param.getAirplane_name());
        }catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @PutMapping("/updateAirplane")
    @Operation(description = "Update Airplane from database")
    public CommonResponse<AirplanesEntity> updateAirport(@RequestBody AirplanesEntity param){
        try {
            AirplanesEntity airplaneEntity = airplanesService.updateAirplane(param);
            log.info(String.valueOf(airplaneEntity), "Successfully updated " + param.getAirplane_name());
            return commonResponseGenerator.succsesResponse(airplaneEntity, "Successfully updated " + param.getAirplane_name());
        }catch (Exception e){
            log.warn(String.valueOf(e));
            return commonResponseGenerator.failedResponse(e.getMessage());
        }
    }

    @DeleteMapping("/deleteAirplane/{airplane_name}")
    @Operation(description = "Delete Airplane from database")
    public CommonResponse<AirplanesEntity> deleteAirport(@PathVariable String airplane_name){
        try{
            AirplanesEntity airplaneEntity = airplanesService.deleteAirplane(airplane_name);
            log.info(String.valueOf(airplaneEntity), "Successfully deleted " + airplaneEntity.getAirplane_name());
            return commonResponseGenerator.succsesResponse(airplaneEntity, "Successfully deleted " + airplaneEntity.getAirplane_name());
        }catch (EmptyResultDataAccessException e){
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Airplane name not found", e);
        }catch (Exception e){
            log.warn(String.valueOf(e));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete airplane", e);
        }
    }
}
