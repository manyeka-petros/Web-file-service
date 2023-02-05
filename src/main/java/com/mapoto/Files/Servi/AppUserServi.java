package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Model.AppUserModels;

import java.util.List;

public interface AppUserServi {
    String registerUser(AppUserModels appUserModels);

    List<AppUser> getAllUsers();

    String removeUser(Long userId);
}
