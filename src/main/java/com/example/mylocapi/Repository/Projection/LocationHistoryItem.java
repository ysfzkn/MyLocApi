package com.example.mylocapi.Repository.Projection;

import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

public interface LocationHistoryItem
{
    String getName();
    LocalDateTime getVisitTime();

    Double getLatitude(); // Location Data (longtitude, latitude)

    Double getLongtitude();

    byte[] getPicByte();


}
