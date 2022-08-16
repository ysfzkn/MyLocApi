package com.example.mylocapi.Repository;

import com.example.mylocapi.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>
{
    // Default Operations
    List<Location> findAll();
}
