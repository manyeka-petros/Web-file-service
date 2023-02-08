package com.mapoto.Files.LogIns;

import com.mapoto.Files.Servi.AppUserServiImpleme;
import com.mapoto.Files.VerifiToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogInsControl {
    @Autowired
    private AppUserServiImpleme appUserServiImpleme;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @PostMapping("/sail")
    public String logIns(@RequestBody LogInRequest logInRequest){
        Authentication authentication;
        try {
           authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    logInRequest.getEmail(),logInRequest.getPassword()
            ));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (BadCredentialsException e){
            throw new BadCredentialsException("The email " + logInRequest.getEmail() + " or " + "   password " +
                    logInRequest.getPassword() +" is not correct");

        }
        return "you are successfully logged in ";
    }
    @GetMapping("/token")
    public String confirmToken(@PathVariable("token" )String token){
        return appUserServiImpleme.confirmToken(token);

    }
}
