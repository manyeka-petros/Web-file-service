package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Entiy.AppUserDetails;
import com.mapoto.Files.Reposito.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AppUserSer implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUsername(username).orElseThrow(
                ()->    new UsernameNotFoundException("The user with email "+ username+ "not found")
        );
        return AppUserDetails.build(appUser);
    }
}
