package com.mapoto.Files.Entiy;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@NoArgsConstructor

public class AppUserDetails implements UserDetails {
    private Long userId;
   private String userName;
    private  String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public AppUserDetails(Long userId, String userName, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static AppUserDetails build(AppUser appUser){
        List<GrantedAuthority> authorities1 = appUser.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getAppUserRoles().name()))
                .collect(Collectors.toList());

        return  new AppUserDetails(
                appUser.getUserId(),
                appUser.getUserName(),
                appUser.getEmail(),
                appUser.getPassword(),
                authorities1
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
