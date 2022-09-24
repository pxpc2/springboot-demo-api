package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSpotService
{

    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(final ParkingSpotRepository parkingSpotRepository)
    {
        this.parkingSpotRepository = parkingSpotRepository;
    }


    @Transactional
    public ParkingSpotModel add(final ParkingSpotModel parkingSpotModel)
    {
        return parkingSpotRepository.save(parkingSpotModel);
    }

    public boolean existsByCarLicensePlate(final String licensePlate)
    {
        return parkingSpotRepository.existsByCarLicensePlate(licensePlate);
    }

    public boolean existsBySpotNumber(final String spotNumber)
    {
        return parkingSpotRepository.existsBySpotNumber(spotNumber);
    }

    public List<ParkingSpotModel> findAll()
    {
        return parkingSpotRepository.findAll();
    }

    public Optional<ParkingSpotModel> findById(final UUID id)
    {
        return parkingSpotRepository.findById(id);
    }

}
