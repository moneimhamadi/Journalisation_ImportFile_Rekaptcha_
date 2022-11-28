package com.moneim.api.repositories;

import com.moneim.api.entities.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<Journal,String> {
}
