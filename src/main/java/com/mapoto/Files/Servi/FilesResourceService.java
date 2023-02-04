package com.mapoto.Files.Servi;

import com.mapoto.Files.Entitys.FilesResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FilesResourceService {
    FilesResource uploadFiles(MultipartFile file) throws Exception;

    FilesResource downloadFile(String fileId) throws Exception;
}
