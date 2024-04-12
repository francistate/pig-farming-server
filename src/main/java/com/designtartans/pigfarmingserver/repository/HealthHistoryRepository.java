package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.HealthHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthHistoryRepository extends JpaRepository<HealthHistory, Long> {
}
