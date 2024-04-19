package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.PigWeightRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PigWeightRecordRepository extends JpaRepository<PigWeightRecord, Long> {
//    @Query("SELECT p FROM PigWeightRecord p WHERE p.pig.id = ?1")
    List<PigWeightRecord> findByPigId(Long pigId);

}
