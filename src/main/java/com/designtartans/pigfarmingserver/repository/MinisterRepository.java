package com.designtartans.pigfarmingserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.Minister;

@Repository
public interface MinisterRepository extends JpaRepository<Minister, Long> {

}
