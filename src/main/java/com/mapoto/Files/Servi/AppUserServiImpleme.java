package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;


import com.mapoto.Files.Entiy.AppUserRoles;
import com.mapoto.Files.Entiy.Roles;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Reposito.AppUserRepository;
import com.mapoto.Files.Reposito.RolesReposito;
import com.mapoto.Files.VerifiToken.VerificationToken;
import com.mapoto.Files.VerifiToken.VerificationTokenRepository;
import com.mapoto.Files.VerifiToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class AppUserServiImpleme implements AppUserServi{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private RolesReposito rolesReposito;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private  AppUserSer appUserSer;



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

        if(appUserRepository.existsByEmail(appUserModels.getEmail())){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"The email entered already exist");
        }
        AppUser appUser = new AppUser();
        appUser.setFirstname(appUserModels.getFirstname());
        appUser.setLastname(appUserModels.getLastname());
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
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(
                token, LocalDate.now(),LocalDate.now().plusDays(1),appUser
        );
        verificationTokenService.saveVerificationToken(verificationToken);

        return "saved";
    }
@Transactional
    public String confirmToken(String token) {
        VerificationToken verificationToken = verificationTokenService.getToken(token).orElseThrow(
                ()-> new IllegalStateException("token not available")
        );
        if(verificationToken.getConfirmedAt() != null){
            throw new IllegalStateException("token is already confirmed");
        }
        LocalDate expiredAt = verificationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDate.now())){
            throw new IllegalStateException("token is alredy expired");
        }
        verificationTokenService.setConfirmedTok(token);
        appUserSer.enableAppUser(verificationToken.getAppUser().getEmail());


        return "confirmed";


    }
}
