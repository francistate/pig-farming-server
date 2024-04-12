package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.PigWeightHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigWeightHistoryRepository extends JpaRepository<PigWeightHistory, Long> {
}
