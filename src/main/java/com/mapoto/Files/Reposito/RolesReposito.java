package com.mapoto.Files.Reposito;

import com.mapoto.Files.Entiy.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesReposito extends JpaRepository<Roles,Long> {
}
