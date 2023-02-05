package com.mapoto.Files.Filez.Servi;

import com.mapoto.Files.Filez.Entitys.FilesResource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesResourceService {
    FilesResource uploadFiles(MultipartFile file) throws Exception;

    FilesResource downloadFile(String fileId) throws Exception;
}
