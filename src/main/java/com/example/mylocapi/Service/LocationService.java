package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Location;

import java.util.List;

public interface LocationService
{
    List<Location> findAllLocations();

    Location saveLocation(Location location);
}
