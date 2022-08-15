package com.example.mylocapi.Controller;

import com.example.mylocapi.Model.Location;
import com.example.mylocapi.Repository.LocationRepository;
import com.example.mylocapi.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController
{
    @Autowired
    LocationService locationService;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping // location GET
    public ResponseEntity<?> getAllLocations()
    {
        return ResponseEntity.ok(locationService.findAllLocations());
    }

    @PostMapping
    public ResponseEntity<?> saveLocation(Location location)
    {
        Point geom = new Point(41.008583,28.980175);
        location.setGeom(geom);
        return new ResponseEntity<>(locationRepository.save(location), HttpStatus.CREATED);
    }
}
