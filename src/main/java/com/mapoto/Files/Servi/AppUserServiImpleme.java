package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Reposito.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public String registerUser(AppUserModels appUserModels) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(appUserModels.getFirstName());
        appUser.setLastName(appUserModels.getLastName());
        appUser.setEmail(appUserModels.getEmail());
        appUser.setPassword(appUserModels.getPassword());
        appUserRepository.save(appUser);
        return "save";
    }
}
