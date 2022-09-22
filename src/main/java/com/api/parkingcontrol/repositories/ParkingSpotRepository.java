package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotRepository
        extends JpaRepository<ParkingSpotModel, UUID>
{

    public boolean existsByCarLicensePlate(final String licensePlate);
    public boolean existsBySpotNumber(final String spotNumber);

}
