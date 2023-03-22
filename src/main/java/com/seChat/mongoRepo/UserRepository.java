package com.seChat.mongoRepo;

import com.seChat.artifacts.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    User findUserByUsername(String username);

    @Query("{username:'?0'}")
    Boolean existsByUsername(String username);

    @Query(value="{username:/?0/}")
    List<User> findAllByUsername(String username);

}
