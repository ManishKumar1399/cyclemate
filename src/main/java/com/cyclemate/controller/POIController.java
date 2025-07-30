package com.cyclemate.controller;

import com.cyclemate.model.POI;
import com.cyclemate.model.Route;
import com.cyclemate.repository.POIRepository;
import com.cyclemate.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pois")
@RequiredArgsConstructor
public class POIController {

    private final POIRepository poiRepository;
    private final RouteRepository routeRepository;

    @GetMapping
    public List<POI> getAllPois() {
        return poiRepository.findAll();
    }

    @PostMapping
    public POI createPoi(@RequestBody POI poi, @RequestParam Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        poi.setRoute(route);
        return poiRepository.save(poi);
    }

    @GetMapping("/route/{routeId}")
    public List<POI> getPoisByRoute(@PathVariable Long routeId) {
        return poiRepository.findByRouteId(routeId);
    }
}
