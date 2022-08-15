package com.example.mylocapi.Repository;

import com.example.mylocapi.Model.LocationHistory;
import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long>
{
    @Query("select l.name as name, l.geom as geom, lh.visitTime as visitTime " +
            "from LocationHistory lh left join Location l on l.id = lh.location.id " +
            "where lh.user.id = :userId")
    List<LocationHistoryItem> findAllLocationHistoryOfUser(@Param("userId") Long userId);
}
