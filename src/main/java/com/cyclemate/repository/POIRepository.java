package com.cyclemate.repository;

import com.cyclemate.model.POI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface POIRepository extends JpaRepository<POI, Long> {
    List<POI> findByRouteId(Long routeId);
}
