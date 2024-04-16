package com.designtartans.pigfarmingserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.Farmer;
import com.designtartans.pigfarmingserver.model.User;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Optional<Farmer> findByUser(User user);
}
