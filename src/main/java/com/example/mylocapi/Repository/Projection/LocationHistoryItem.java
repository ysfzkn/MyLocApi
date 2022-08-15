package com.example.mylocapi.Repository.Projection;

import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

public interface LocationHistoryItem
{
    String getName();

    Point getGeom(); // Location Data (longtitude, latitude)

    LocalDateTime getVisitTime();
}
