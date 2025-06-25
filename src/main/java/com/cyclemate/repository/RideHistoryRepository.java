package com.cyclemate.repository;

import com.cyclemate.model.RideHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RideHistoryRepository extends JpaRepository<RideHistory, Long> {
    List<RideHistory> findByUserId(Long userId);
}
