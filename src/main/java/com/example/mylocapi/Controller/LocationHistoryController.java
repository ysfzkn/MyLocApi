package com.example.mylocapi.Controller;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Request.LocationHistoryItemCreateRequest;
import com.example.mylocapi.Response.LocationHistoryResponse;
import com.example.mylocapi.Service.LocationHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("history")
public class LocationHistoryController
{
    private final LocationHistoryService locationHistoryService;

    private byte[] bytes;

    public LocationHistoryController(LocationHistoryService locationHistoryService) {
        this.locationHistoryService = locationHistoryService;
    }

    @GetMapping("{user_id}")
    public ResponseEntity<?> getLocationHistoriesOfUser(@PathVariable Long user_id)
    {
        return new ResponseEntity<>(locationHistoryService.findLocationHistoryOfUser(user_id),
                                        HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllHistories()
    {
        return new ResponseEntity<>(locationHistoryService.getAllHistories(),HttpStatus.OK);
    }


    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file)
    {
        try
        {
            System.out.println(file);
            this.bytes = file.getBytes();
            System.out.println("Photo added successfully!");
            return new ResponseEntity<>("Photo added successfully!",HttpStatus.OK);
        }
        catch(IOException e)
        {
            return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping// Location History POST
    public ResponseEntity<?> saveLocationHistory(@RequestBody LocationHistoryItemCreateRequest locationHistory)
    {
        LocationHistoryResponse locationHistoryResponse = new LocationHistoryResponse();

        try
        {
            System.out.println("Post Request for History");

            locationHistory.setPicByte(this.bytes);
            LocalDateTime datetime = LocalDateTime.now();
            locationHistory.setVisitTime(datetime);

            LocationHistory locationHistoryRes = locationHistoryService.
                                    createOneLocationHistory(locationHistory);

            locationHistoryResponse.setPicByte(this.bytes);
            locationHistoryResponse.setVisitTime(datetime);
            locationHistoryResponse.setId(locationHistoryRes.getId());

            this.bytes = null;
            return new ResponseEntity<>(locationHistoryResponse, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

}
