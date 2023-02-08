package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;


import com.mapoto.Files.Entiy.AppUserRoles;
import com.mapoto.Files.Entiy.Roles;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Reposito.AppUserRepository;
import com.mapoto.Files.Reposito.RolesReposito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RolesReposito rolesReposito;



    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public String removeUser(Long userId) {
      appUserRepository.deleteById(userId);
        return "the user with id  " + userId+ " is deleted";
    }

    @Override
    public String saveRole(Roles roles) {
        rolesReposito.save(roles);
        return "saved";
    }

    @Override
    public String registerUsers(AppUserModels appUserModels) {
        if(appUserRepository.existsByUsername(appUserModels.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The username already exist");
        }
        if(appUserRepository.existsByEmail(appUserModels.getEmail())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email entered already exist");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserModels.getUsername());
        appUser.setEmail(appUserModels.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserModels.getPassword()));
        Set<String> roleString = appUserModels.getRoles();
        Set<Roles> roles = new HashSet<>();
        if(roleString == null){
            Roles userRole = rolesReposito.findByName(AppUserRoles.ROLE_USER).orElseThrow(
                    ()-> new RuntimeException("User role not found")
            );
            roles.add(userRole);
        }
        else {
            roleString.forEach(
                    role-> {
                        switch (role){
                            case "mod":
                                Roles moderator = rolesReposito.findByName(AppUserRoles.ROLE_MODERATOR).orElseThrow(
                                        ()-> new RuntimeException("The moderator role is not found")
                                );
                                roles.add(moderator);
                                break;
                            case "admin":
                                Roles admin = rolesReposito.findByName(AppUserRoles.ROLE_ADMIN).orElseThrow(
                                        ()-> new RuntimeException("The addmi not found ")
                                );
                                roles.add(admin);
                                break;
                            default:
                                Roles userRole = rolesReposito.findByName(AppUserRoles.ROLE_USER).orElseThrow(
                                        () -> new RuntimeException("user not found")
                                );
                                roles.add(userRole);

                        }
                    }
            );
        }
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return "saved";
    }
}
