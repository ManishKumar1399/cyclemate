package com.cyclemate.controller;

import com.cyclemate.model.POI;
import com.cyclemate.repository.POIRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pois")
@RequiredArgsConstructor
public class POIController {

    private final POIRepository poiRepository;

    @GetMapping
    public List<POI> getAllPois() {
        return poiRepository.findAll();
    }

    @PostMapping
    public POI createPoi(@RequestBody POI poi) {
        return poiRepository.save(poi);
    }

    @GetMapping("/route/{routeId}")
    public List<POI> getPoisByRoute(@PathVariable Long routeId) {
        return poiRepository.findByRouteId(routeId);
    }
}
