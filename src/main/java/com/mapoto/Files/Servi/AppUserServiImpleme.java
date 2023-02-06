package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Reposito.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public String registerUser(AppUserModels appUserModels) {
        if(appUserRepository.existsByUsername(appUserModels.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exist in system");
        }
        if (appUserRepository.existsByEmail(appUserModels.getEmail())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email already exist in system");
        }
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserModels.getUsername());
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
