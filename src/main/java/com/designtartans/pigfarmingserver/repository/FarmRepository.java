package com.designtartans.pigfarmingserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.Farm;

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
