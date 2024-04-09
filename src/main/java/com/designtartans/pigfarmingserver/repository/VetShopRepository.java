package com.designtartans.pigfarmingserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.designtartans.pigfarmingserver.model.VetShop;

@Repository
public interface VetShopRepository extends JpaRepository<VetShop, Long> {
    Optional<VetShop> findByVetShopName(String vetShopName);
}
