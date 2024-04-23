package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.PigHealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PigHealthRecordRepository extends JpaRepository<PigHealthRecord, Long> {

    // @Query("SELECT p FROM PigHealthRecord p WHERE p.pig.id = ?1")
    List<PigHealthRecord> findByPigId(Long pigId);
}
