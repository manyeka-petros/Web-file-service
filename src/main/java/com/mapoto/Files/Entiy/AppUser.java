package com.mapoto.Files.Entiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();
    private boolean enabled;
    private boolean isLocked = false;

    public AppUser(String firstname, String lastname, String email, String password, Set<Roles> roles, boolean enabled) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
    }
    public boolean isEnabled() {
        return enabled;
    }
}
