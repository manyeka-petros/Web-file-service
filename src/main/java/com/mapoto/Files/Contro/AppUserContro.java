package com.mapoto.Files.Contro;

import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Servi.AppUserServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserContro {
    @Autowired
    private AppUserServi appUserServi;

    @PostMapping
    public String registerUsers(@RequestBody AppUserModels appUserModels){
        return appUserServi.registerUser(appUserModels);

    }
}
