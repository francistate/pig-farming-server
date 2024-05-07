package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.dto.ProvincePigCountDTO;
import com.designtartans.pigfarmingserver.model.Pig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PigRepository extends JpaRepository<Pig, Long> {
        List<Pig> findByFarmId(Long farmId);

        Optional<Pig> findByTag(String tag);

        @Query("SELECT COUNT(p) FROM Pig p WHERE p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE")
        Long countAllActivePigs();

        @Query("SELECT NEW com.designtartans.pigfarmingserver.dto.ProvincePigCountDTO(COALESCE(p.farm.province, 'UNKNOWN'), COUNT(p)) FROM Pig p WHERE p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE GROUP BY COALESCE(p.farm.province, 'UNKNOWN')")
        List<ProvincePigCountDTO> countActivePigsPerProvince();

        @Query("SELECT p.gender, COUNT(p) " +
                        "FROM Pig p " +
                        "WHERE p.farm.id = :farmId AND p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE  "
                        +
                        "GROUP BY p.gender")
        List<Object[]> countPigsByGenderForAFarm(@Param("farmId") Long farmId);

        @Query("SELECT p.breed, COUNT(p) " +
                        "FROM Pig p " +
                        "WHERE p.farm.id = :farmId AND p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE  "
                        +
                        "GROUP BY p.breed")
        List<Object[]> countPigsByBreedForAFarm(@Param("farmId") Long id);

        @Query("SELECT p.breed, COUNT(p) " +
                "FROM Pig p " +
                "WHERE p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE  " +
                "GROUP BY p.breed")
        List<Object[]> countPigsByBreedForAllFarmsCombined();

        List<Pig> findByFarm_Province(String province);

        List<Pig> findByFarm_District(String district);

        @Query("SELECT p.gender, COUNT(p) " +
                "FROM Pig p " +
                "WHERE p.pigStatus = com.designtartans.pigfarmingserver.model.PigStatus.ACTIVE  " +
                "GROUP BY p.gender")
        List<Object[]> countPigsByGenderForAllFarmsCombined();
}
