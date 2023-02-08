package com.mapoto.Files.Model;

import com.mapoto.Files.Entiy.AppUserRoles;
import com.mapoto.Files.Entiy.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserModels {
    private String firstname;
    private String lastname;

    private  String email;
    private String password;
    private Set<String> roles;


}
