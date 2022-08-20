package com.example.mylocapi.Model;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.springframework.data.geo.Point;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime visitTime;

    @Column(name = "picByte", length = 4000, nullable = false)
    @Lob
    private byte[] picByte; // picture data
    @ManyToOne(fetch = FetchType.LAZY,
                cascade = CascadeType.ALL )
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL )
    @JoinColumn(name="location_id", nullable = false)
    private Location location;
}
