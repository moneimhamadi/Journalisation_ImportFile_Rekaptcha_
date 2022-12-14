package com.moneim.api.services;

import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {
    public ObjectResponse getAllRoles();
    public ObjectResponse addRole(Role role,String idTheDoerUser);
    public ObjectResponse deleteRole(String idRole,String idTheDoerUser);
    public ObjectResponse editUserRoles(String idUser,List<String> roles,String idTheDoear);
}
