package com.mapoto.Files.LogIns;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LogInRequest {
    private String email;
    private String password;
}
