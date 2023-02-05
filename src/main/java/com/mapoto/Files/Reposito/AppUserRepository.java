package com.mapoto.Files.Reposito;

import com.mapoto.Files.Entiy.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    //AppUser findAppUserByEmail(String email);


    Optional<AppUser>  findAppUserByEmail(String email);
}
