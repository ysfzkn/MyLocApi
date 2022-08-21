package com.example.mylocapi.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationHistoryResponse
{
    Long id;
    LocalDateTime visitTime;
    String comment;
    byte[] picByte;
}
