package com.designtartans.pigfarmingserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
