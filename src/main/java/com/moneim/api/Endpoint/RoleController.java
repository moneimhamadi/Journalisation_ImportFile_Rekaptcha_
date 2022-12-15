package com.moneim.api.Endpoint;


import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.Role;
import com.moneim.api.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @GetMapping("/getAllRoles")
    public ObjectResponse getAllRoles(){
        return  roleService.getAllRoles();
    }

    @PostMapping("/addRole/{idTheDoearUser}")
    public ObjectResponse addRole(@RequestBody Role role, @PathVariable String idTheDoearUser){
        return roleService.addRole(role,idTheDoearUser);
    }

    @DeleteMapping ("/deleteRole/{idRole}/{idTheDoerUser}")
    public ObjectResponse deleteRole(@PathVariable String idRole,@PathVariable String idTheDoerUser){
        return roleService.deleteRole(idRole,idTheDoerUser);
    }

    @PutMapping("/updateUserRoles/{idUser}/{idTheDoear}")
    public ObjectResponse updateUserRoles (@PathVariable String idUser, @RequestBody List<String> roles, @PathVariable String idTheDoear){
        return  roleService.editUserRoles(idUser,roles,idTheDoear);
    }
}
