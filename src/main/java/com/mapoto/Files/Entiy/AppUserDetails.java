package com.mapoto.Files.Entiy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter

@NoArgsConstructor

public class AppUserDetails implements UserDetails {
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;

    private String password;

    public AppUserDetails(Long userId, String firstname, String lastname, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    private Collection<? extends GrantedAuthority> authorities;



    public static AppUserDetails build(AppUser appUser){
        List<GrantedAuthority> authorities1 = appUser.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());

        return  new AppUserDetails(
                appUser.getUserId(),
                appUser.getFirstname(),
                appUser.getLastname(),
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
        return email;
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
