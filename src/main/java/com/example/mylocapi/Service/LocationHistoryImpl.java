package com.example.mylocapi.Service;

import com.example.mylocapi.Model.Location;
import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Model.User;
import com.example.mylocapi.Repository.LocationHistoryRepository;
import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import com.example.mylocapi.Request.LocationHistoryItemCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class LocationHistoryImpl implements LocationHistoryService
{
    private final LocationHistoryRepository locationHistoryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    public LocationHistoryImpl(LocationHistoryRepository locationHistoryRepository)
    {
        this.locationHistoryRepository = locationHistoryRepository;
    }

    @Override
    public List<LocationHistory> getAllHistories()
    {
        return locationHistoryRepository.findAll();
    }
    @Override
    public List<LocationHistoryItem> findLocationHistoryOfUser(Long userId)
    {
        return locationHistoryRepository.findAllLocationHistoryOfUser(userId);
    }

    @Override
    public LocationHistory createOneLocationHistory(LocationHistoryItemCreateRequest
                                                                newHistoryRequest)
    {
        User user = userService.getOneUserById(newHistoryRequest.getUser_id());
        Location location = locationService.getLocationById(newHistoryRequest.
                                                                getLocation_id());
        if (user == null)
        {
            return null;
        }
        userService.updateCardBalance(user.getId(),location.getPrice());

        LocationHistory toSave = new LocationHistory();

        toSave.setPicByte(newHistoryRequest.getPicByte());
        toSave.setVisitTime(LocalDateTime.now());
        toSave.setUser(user);
        toSave.setLocation(location);

        return locationHistoryRepository.save(toSave);
    }

}
