package com.mapoto.Files.Servi;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Reposito.AppUserRepository;
import com.mapoto.Files.VerifiToken.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;


@Service
public class AppUserSer implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByEmail(email).orElseThrow(
                ()->    new UsernameNotFoundException("The user with email "+ email+ "not found")
        );

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        });
        return new User(appUser.getEmail(), appUser.getPassword(), authorities);
    }
    public  int enableAppUser(String email){
        return appUserRepository.enableAppUser(email);
    }
}
