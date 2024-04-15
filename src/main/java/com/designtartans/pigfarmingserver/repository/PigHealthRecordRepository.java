package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.PigHealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigHealthRecordRepository extends JpaRepository<PigHealthRecord, Long> {
}
