package com.designtartans.pigfarmingserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

}
