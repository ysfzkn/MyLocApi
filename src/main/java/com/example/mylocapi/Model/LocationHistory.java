package com.example.mylocapi.Model;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "location_history")
public class LocationHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column( name = "visit_time", nullable = false)
    private LocalDateTime visitTime;

    // foto

    @ManyToOne(fetch = FetchType.LAZY,
                cascade = { CascadeType.ALL })
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = { CascadeType.ALL })
    @JoinColumn(name="location_id", nullable=false)
    private Location location;
}
