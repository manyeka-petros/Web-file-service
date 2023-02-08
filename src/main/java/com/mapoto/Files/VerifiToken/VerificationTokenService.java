package com.mapoto.Files.VerifiToken;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public void  saveVerificationToken(VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }


    public Optional<VerificationToken> getToken(String token){
        return verificationTokenRepository.findByToken(token);
    }
    public int setConfirmedTok(String token){
        return verificationTokenRepository.updateToken(token, LocalDate.now());
    }
}
