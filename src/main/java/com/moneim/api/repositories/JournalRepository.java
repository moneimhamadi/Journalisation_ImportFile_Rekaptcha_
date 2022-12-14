package com.moneim.api.repositories;

import com.moneim.api.entities.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<Journal, String> {
    List<Journal> findByIdUser(String idUser);
    List<Journal> findByOperation(String operation);
}
