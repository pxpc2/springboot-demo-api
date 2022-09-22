package com.api.parkingcontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDTO // Data Transfer Object
{

    @NotBlank
    private String spotNumber;

    @NotBlank
    @Size(max = 7)
    private String carLicensePlate;

    @NotBlank
    private String carModel;

    @NotBlank
    private String driverName;

    @NotBlank
    private String apartment;




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
}
