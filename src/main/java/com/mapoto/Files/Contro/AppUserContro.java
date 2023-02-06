package com.mapoto.Files.Contro;

import com.mapoto.Files.Entiy.AppUser;
import com.mapoto.Files.Model.AppUserModels;
import com.mapoto.Files.Servi.AppUserServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppUserContro {
    @Autowired
    private AppUserServi appUserServi;

    @PostMapping("/posts")
    public String registerUsers(@RequestBody AppUserModels appUserModels){
        return appUserServi.registerUser(appUserModels);

    }
    @GetMapping("/gets")
    public List<AppUser> getAllUsers(){
        return appUserServi.getAllUsers();
    }
    @DeleteMapping("/del/{userId}")

    public String removeUser(@PathVariable Long userId){
        return appUserServi.removeUser(userId);
    }
    @GetMapping("/check")
    public String check(){
        return "check mapoto prlease";
    }
    @GetMapping("/look")
    public String view(){
        return "view  mapoto status";
    }
}
