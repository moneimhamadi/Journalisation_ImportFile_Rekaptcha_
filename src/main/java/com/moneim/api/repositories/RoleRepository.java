package com.moneim.api.repositories;

import com.moneim.api.entities.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    public Role findByNom(String nom);
    public Role findByIdRole(String idRole);
}
