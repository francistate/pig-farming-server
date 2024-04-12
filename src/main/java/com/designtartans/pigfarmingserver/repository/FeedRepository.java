package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
}
