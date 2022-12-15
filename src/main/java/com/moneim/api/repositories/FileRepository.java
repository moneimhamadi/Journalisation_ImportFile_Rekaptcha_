package com.moneim.api.repositories;

import com.moneim.api.entities.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends MongoRepository<File ,String> {
    public List<File> findByIdUser(String idUser);
    public List<File> findByNomFile(String nomFile);
}
