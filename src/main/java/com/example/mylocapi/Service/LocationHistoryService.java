package com.example.mylocapi.Service;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import com.example.mylocapi.Request.LocationHistoryItemCreateRequest;

import java.util.List;

public interface LocationHistoryService
{
    List<LocationHistory> getAllHistories();

    List<LocationHistoryItem> findLocationHistoryOfUser(Long userId);

    LocationHistory createOneLocationHistory(LocationHistoryItemCreateRequest
                                                     locationHistoryItemCreateRequest);
}
