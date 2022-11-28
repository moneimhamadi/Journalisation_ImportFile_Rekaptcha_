package com.moneim.api.repositories;

import com.moneim.api.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository  extends MongoRepository<User, String> {
    User findByEmail(String email);
    User findByUsername(String username);
}
