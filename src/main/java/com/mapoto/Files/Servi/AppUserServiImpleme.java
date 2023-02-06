package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Reposito.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public String registerUser(AppUserModels appUserModels) {
        AppUser appUser = new AppUser();
        appUser.setUserName(appUserModels.getUserName());
        appUser.setEmail(appUserModels.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserModels.getPassword()));
        appUserRepository.save(appUser);
        return "save";
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public String removeUser(Long userId) {
      appUserRepository.deleteById(userId);
        return "the user with id  " + userId+ " is deleted";
    }
}
