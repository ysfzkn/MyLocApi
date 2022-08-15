package com.example.mylocapi.Service;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Repository.LocationHistoryRepository;
import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocationHistoryImpl implements LocationHistoryService
{
    private final LocationHistoryRepository locationHistoryRepository;

    public LocationHistoryImpl(LocationHistoryRepository locationHistoryRepository)
    {
        this.locationHistoryRepository = locationHistoryRepository;
    }

    @Override
    public LocationHistory saveLocationHistory(LocationHistory locationHistory)
    {
        locationHistory.setVisitTime(LocalDateTime.now());
        return locationHistoryRepository.save(locationHistory);
    }

    @Override
    public List<LocationHistoryItem> findLocationHistoryOfUser(Long userId)
    {
        return locationHistoryRepository.findAllLocationHistoryOfUser(userId);
    }
}
