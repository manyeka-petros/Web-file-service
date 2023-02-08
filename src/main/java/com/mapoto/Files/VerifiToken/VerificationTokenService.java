package com.mapoto.Files.VerifiToken;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public void  saveVerificationToken(VerificationToken verificationToken){
        verificationTokenRepository.save(verificationToken);
    }

}
