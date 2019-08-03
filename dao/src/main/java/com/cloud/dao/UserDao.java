package com.cloud.dao;

import com.cloud.core.BaseRepository;
import com.cloud.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao  extends BaseRepository<User> {

}
