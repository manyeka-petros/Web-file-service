package com.mapoto.Files.Reposit;

import com.mapoto.Files.Entitys.FilesResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesResourceRepository extends JpaRepository<FilesResource,String> {
}
