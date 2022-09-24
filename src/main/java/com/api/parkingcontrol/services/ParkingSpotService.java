package com.api.parkingcontrol.services;

import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<ParkingSpotModel> findAll(final Pageable pageable)
    {
        return parkingSpotRepository.findAll(pageable);
    }

    public Optional<ParkingSpotModel> findById(final UUID id)
    {
        return parkingSpotRepository.findById(id);
    }

    public void delete(final ParkingSpotModel parkingSpotModel)
    {
        parkingSpotRepository.delete(parkingSpotModel);
    }

}
