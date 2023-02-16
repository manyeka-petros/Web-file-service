package com.mapoto.Files.LogIns;

import com.mapoto.Files.JwtUtility.JwtUtilty;
import com.mapoto.Files.Servi.AppUserSer;
import com.mapoto.Files.Servi.AppUserServiImpleme;
import com.mapoto.Files.VerifiToken.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LogInsControl {
    @Autowired
    private AppUserServiImpleme appUserServiImpleme;
    @Autowired
    private  AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtilty jwtUtilty;
    @Autowired
    private AppUserSer appUserSer;
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
        //GENERATING TOKEN OF BEING AUTHENTICATED
        final UserDetails userDetails = appUserSer.loadUserByUsername(logInRequest.getEmail());

        return "your token is  " + jwtUtilty.generateToken(userDetails) ;
    }
    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token){
        return appUserServiImpleme.confirmToken(token);

    }
}
