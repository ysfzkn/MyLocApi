package com.example.mylocapi.Controller;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Service.LocationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/history")
public class LocationHistoryController
{
    @Autowired
    private LocationHistoryService locationHistoryService;
    private byte[] bytes;
    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException
    {
        System.out.println(file);
        this.bytes = file.getBytes();
    }

    @PostMapping // Location History POST
    public ResponseEntity<?> saveLocationHistory(@RequestBody LocationHistory locationHistory)
    {
        locationHistory.setPicByte(this.bytes);
        locationHistoryService.saveLocationHistory(locationHistory);
        this.bytes = null;
        return new ResponseEntity<>(locationHistory, HttpStatus.CREATED);
    }

    /*
    // Authentication Layer ??
    @GetMapping // location-history
    public ResponseEntity<?> getAllHistoryOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return ResponseEntity.ok(LocationHistoryService.findLocationHistoryOfUser(userPrincipal.getId()));
    }
    */

}
