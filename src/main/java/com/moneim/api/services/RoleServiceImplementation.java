package com.moneim.api.services;

import com.moneim.api.entities.Journal;
import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.Role;
import com.moneim.api.entities.User;
import com.moneim.api.repositories.JournalRepository;
import com.moneim.api.repositories.RoleRepository;
import com.moneim.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class RoleServiceImplementation implements IRoleService {
    Logger logger = LoggerFactory.getLogger(RoleServiceImplementation.class);

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JournalRepository journalRepositoy;
    @Autowired
    UserRepository userRepository;

    @Override
    public ObjectResponse getAllRoles() {
        logger.info("Role Service Impl  : Getting All Roles ");
        try {
            Long nbrOfRoles = roleRepository.count();
            if (nbrOfRoles == 0) {
                return new ObjectResponse("Empty list roles",
                        null,
                        0);
            } else return new ObjectResponse("List roles found ",
                    roleRepository.findAll(),
                    1);
        } catch (Exception e) {
            return new ObjectResponse(" Erreur " + e.getMessage(),
                    null,
                    2);
        }
    }

    @Override
    public ObjectResponse addRole(Role role, String idTheDoerUser) {
        logger.info("Role Service Impl Sign Up : creation new Role:Start -input => Role : " + role.getNom());

        try {
            if ((roleRepository.findByNom(role.getNom()) != null)) {
                return new ObjectResponse("Role déja existe!!! Changer le role ",
                        roleRepository.findByNom(role.getNom()),
                        0);
            } else {
                Role r = roleRepository.save(new Role(role.getNom()));
                Journal journal = journalRepositoy.save(new Journal(idTheDoerUser,
                        (userRepository.findByIdUser(idTheDoerUser)).getNom() + " " + (userRepository.findByIdUser(idTheDoerUser)).getPrenom(),
                        "Create",
                        "Role Creation",
                        new Date(),
                        "Role"));
                return new ObjectResponse("Role ajouté avec succés",
                        r,
                        1);
            }

        } catch (Exception e) {
            return new ObjectResponse("Erreur",
                    e,
                    2);
        }
    }

    @Override
    public ObjectResponse deleteRole(String idRole,String idTheDoerUser) {
        logger.info("Role Service Impl Delete User Up : Deleting role with id : " + idRole);
        try {
            Role role = roleRepository.findByIdRole(idRole);
            if (null != role) {
                roleRepository.deleteById(idRole);
                Journal journal = journalRepositoy.save(new Journal(idTheDoerUser,
                        (userRepository.findByIdUser(idTheDoerUser)).getNom() + " " + (userRepository.findByIdUser(idTheDoerUser)).getPrenom(),
                        "Delete",
                        "Delete Role",
                        new Date(),
                        "Role"));
                return new ObjectResponse("Role Deleted ",
                        null,
                        1);
            }

            return new ObjectResponse("Role Not Found ",
                    null,
                    0);
        } catch (Exception e) {
            return new ObjectResponse("Erreur",
                    e,
                    2);
        }
    }
}
