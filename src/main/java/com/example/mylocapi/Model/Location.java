package com.example.mylocapi.Model;

import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Data
@Entity
@Table( name = "location")
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column( name = "geom", nullable = false)
    private Point geom;



}
