package com.example.mylocapi.Controller;

import com.example.mylocapi.Model.Location;
import com.example.mylocapi.Repository.LocationRepository;
import com.example.mylocapi.Service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("location")
public class LocationController
{
    final
    LocationService locationService;

    final
    LocationRepository locationRepository;

    public LocationController(LocationService locationService, LocationRepository locationRepository)
    {
        this.locationService = locationService;
        this.locationRepository = locationRepository;
    }

    @GetMapping // location GET
    public ResponseEntity<?> getAllLocations()
    {
        return ResponseEntity.ok(locationService.findAllLocations());
    }

    @DeleteMapping("{id}") // location/{id} DELETE
    public ResponseEntity<?> deleteInstrumentById(@PathVariable Long id)
    {
        locationService.deleteLocationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveLocation(@RequestBody Location location)
    {
        return new ResponseEntity<>(locationService.saveLocation(location), HttpStatus.CREATED);
    }
}
