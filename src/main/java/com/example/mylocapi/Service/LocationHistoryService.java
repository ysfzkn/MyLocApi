package com.example.mylocapi.Service;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Repository.Projection.LocationHistoryItem;

import java.util.List;

public interface LocationHistoryService
{
    LocationHistory saveLocationHistory(LocationHistory locationHistory);

    List<LocationHistoryItem> findLocationHistoryOfUser(Long userId);
}
