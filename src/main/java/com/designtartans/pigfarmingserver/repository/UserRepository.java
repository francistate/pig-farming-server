package com.designtartans.pigfarmingserver.repository;

import com.designtartans.pigfarmingserver.model.User;

public interface UserRepository extends BaseRepository<User> {
    User findByPhoneNumber(String phoneNumber);

    User findByPhoneNumberAndPassword(String phoneNumber, String password);
}
