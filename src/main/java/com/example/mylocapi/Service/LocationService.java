package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService
{
    List<Location> findAllLocations();

    Location getLocationById(Long id);

    Location saveLocation(Location location);

    Location deleteLocationById(Long id);
}
