package com.api.parkingcontrol.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity //
@Table(name = ParkingSpotModel.TABLE_NAME)
public class ParkingSpotModel implements Serializable
{
    public static final String TABLE_NAME = "PARKING_SPOT";
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String spotNumber;
    @Column(nullable = false, unique = true, length = 7)
    private String carLicensePlate;
    private String carModel;
    private String driverName;
    private String apartment;
    private LocalDateTime checkinDate;



    public UUID getId()
    {
        return id;
    }

    public void setId(final UUID id)
    {
        this.id = id;
    }

    public String getSpotNumber()
    {
        return spotNumber;
    }

    public void setSpotNumber(final String spotNumber)
    {
        this.spotNumber = spotNumber;
    }

    public String getCarLicensePlate()
    {
        return carLicensePlate;
    }

    public void setCarLicensePlate(final String carLicensePlate)
    {
        this.carLicensePlate = carLicensePlate;
    }

    public String getCarModel()
    {
        return carModel;
    }

    public void setCarModel(final String carModel)
    {
        this.carModel = carModel;
    }

    public String getDriverName()
    {
        return driverName;
    }

    public void setDriverName(final String driverName)
    {
        this.driverName = driverName;
    }

    public String getApartment()
    {
        return apartment;
    }

    public void setApartment(final String apartment)
    {
        this.apartment = apartment;
    }

    public LocalDateTime getCheckinDate()
    {
        return checkinDate;
    }

    public void setCheckinDate(final LocalDateTime checkinDate)
    {
        this.checkinDate = checkinDate;
    }
}
