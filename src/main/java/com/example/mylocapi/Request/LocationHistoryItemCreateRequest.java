package com.example.mylocapi.Request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationHistoryItemCreateRequest
{
    LocalDateTime visitTime;
    byte[] picByte;
    Long user_id;
    Long location_id;
}
