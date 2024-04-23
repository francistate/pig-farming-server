package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.Pig;
import com.designtartans.pigfarmingserver.model.PigWeightRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigWeightRecordRepository extends JpaRepository<PigWeightRecord, Long> {

    List<PigWeightRecord> findByPig(Pig pig);

}
