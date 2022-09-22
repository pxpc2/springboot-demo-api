package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController
{

    private final ParkingSpotService parkingSpotService;

    public ParkingSpotController(final ParkingSpotService parkingSpotService)
    {
        this.parkingSpotService = parkingSpotService;
    }


    @PostMapping
    public ResponseEntity<Object> addParkingSpot(
            @RequestBody @Valid final ParkingSpotDTO parkingSpotDTO)
    {

        // verificações de condições (separar depois em um VALIDATOR)
        if (parkingSpotService.existsByCarLicensePlate(parkingSpotDTO.getCarLicensePlate()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "Conflito: Placa do carro já existe no estacionamento."
            );
        if (parkingSpotService.existsBySpotNumber(parkingSpotDTO.getSpotNumber()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    "Conflito: Vaga já em uso."
            );

        final var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDTO, parkingSpotModel);
        parkingSpotModel.setCheckinDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                parkingSpotService.add(parkingSpotModel)
        );
    }

}
