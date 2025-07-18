package com.cyclemate.repository;

import com.cyclemate.model.Route;
import com.cyclemate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
    // You can add search by location later if needed
    List<Route> findByCreatedBy(User createdBy);
}
