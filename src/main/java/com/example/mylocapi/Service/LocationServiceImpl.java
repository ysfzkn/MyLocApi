package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Location;
import com.example.mylocapi.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService
{
    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> findAllLocations()
    {
        return locationRepository.findAll();
    }

    @Override
    public Location saveLocation(Location location)
    {
        System.out.println(location.getName());
        System.out.println(location.getGeom());
        return locationRepository.save(location);
    }
}
