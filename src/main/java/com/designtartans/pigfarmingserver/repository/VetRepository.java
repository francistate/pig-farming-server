package com.designtartans.pigfarmingserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.User;
import com.designtartans.pigfarmingserver.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Optional<Vet> findByUser(User user);
}
