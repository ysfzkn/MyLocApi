package com.example.mylocapi.Repository;

import com.example.mylocapi.Model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>
{
    // Default Operations
}
