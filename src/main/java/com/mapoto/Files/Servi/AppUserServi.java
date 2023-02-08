package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Entiy.Roles;
import com.mapoto.Files.Model.AppUserModels;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.List;

public interface AppUserServi {


    List<AppUser> getAllUsers();

    String removeUser(Long userId);

    String saveRole(Roles roles);

    String registerUsers(AppUserModels appUserModels);
}
