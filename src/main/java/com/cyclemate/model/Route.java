package com.cyclemate.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String startLocation;
    private String endLocation;
    private Double distanceKm;
    private Integer elevationGain;
    private Integer trafficScore;

    @Column(columnDefinition = "TEXT")
    private String mapPath; // JSON or coordinates

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
