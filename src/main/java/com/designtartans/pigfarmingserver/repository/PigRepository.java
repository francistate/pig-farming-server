package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.Pig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigRepository extends JpaRepository<Pig, Long> {
}
