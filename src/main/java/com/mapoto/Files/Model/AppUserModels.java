package com.mapoto.Files.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserModels {
    private String firstName;
    private String lastName;
    private  String email;
    private String password;
}
