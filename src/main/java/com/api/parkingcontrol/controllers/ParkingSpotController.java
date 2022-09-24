package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<Page<ParkingSpotModel>> getAllParkingSpots
            (@PageableDefault(page = 0, size = 10, sort = "id",
            direction = Sort.Direction.ASC) final Pageable pageable)
    {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParkingSpotById
            (@PathVariable(value = "id") final UUID id)
    {
        final Optional<ParkingSpotModel> parkingSpotModelOptional
                = parkingSpotService.findById(id);
        return parkingSpotModelOptional.<ResponseEntity<Object>>map(parkingSpotModel
                -> ResponseEntity.status(HttpStatus.OK).body(parkingSpotModel)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpotById
            (@PathVariable(value = "id") final UUID id)
    {
        final Optional<ParkingSpotModel> parkingSpotModelOptional
                = parkingSpotService.findById(id);
        if (parkingSpotModelOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    "Vaga não existente."
            );
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot
            (@PathVariable(value = "id") final UUID id,
             @RequestBody @Valid final ParkingSpotDTO parkingSpotDTO)
    {
        final Optional<ParkingSpotModel> parkingSpotModelOptional
                = parkingSpotService.findById(id);
        if (parkingSpotModelOptional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não existente");

        final var parkingSpotModel
                = transferData(parkingSpotModelOptional.get(), parkingSpotDTO);

        return ResponseEntity.status(HttpStatus.OK).body(
                parkingSpotService.add(parkingSpotModel)
        );
    }

    private ParkingSpotModel transferData(final ParkingSpotModel p,
                                          final ParkingSpotDTO d)
    {
        p.setSpotNumber(d.getSpotNumber());
        p.setCarModel(d.getCarModel());
        p.setApartment(d.getApartment());
        p.setDriverName(d.getDriverName());
        p.setCarLicensePlate(d.getCarLicensePlate());
        return p;
    }

}
