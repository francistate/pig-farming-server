package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.PigWeightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigWeightRecordRepository extends JpaRepository<PigWeightRecord, Long> {
}
