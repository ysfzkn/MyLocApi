package com.example.mylocapi.Model;

import com.example.mylocapi.Repository.Projection.LocationHistoryItem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table( name = "locations")
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "name", nullable = false)
    private String name;

    @Column( name = "longtitude", nullable = false)
    private Double longtitude;

    @Column( name = "latitude", nullable = false)
    private Double latitude;

    @Column( name = "price", nullable = false)
    private Long price;

}
