package com.mapoto.Files.Filez.Reposit;

import com.mapoto.Files.Filez.Entitys.FilesResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesResourceRepository extends JpaRepository<FilesResource,String> {
}
