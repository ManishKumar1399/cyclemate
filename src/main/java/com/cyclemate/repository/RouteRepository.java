package com.cyclemate.repository;

import com.cyclemate.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
    // You can add search by location later if needed
}
