package com.mapoto.Files.Filez.Contro;

import com.mapoto.Files.Filez.Entitys.FilesResource;
import com.mapoto.Files.Filez.Models.FilesModels;
import com.mapoto.Files.Filez.Servi.FilesResourceImplement;
import com.mapoto.Files.Filez.Servi.FilesResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:8081")

public class FilesResourceControl {
    @Autowired
    private FilesResourceService filesResourceService;
    @Autowired
    private FilesResourceImplement filesResourceImplement;

    @PostMapping( value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public FilesModels uploadFiles(@RequestParam("file") MultipartFile file) throws Exception {
        //FilesResource filesResource = null;
        String downloadUrl = "";
      FilesResource  filesResource = filesResourceService.uploadFiles(file);

        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/")
                .path(filesResource.getFileId()).toUriString();

        return new FilesModels(filesResource.getFilename(), downloadUrl,file.getContentType(),file.getSize());
    }

    @GetMapping("/download/{fileId}")

    public ResponseEntity<Resource> getFile(@PathVariable String fileId) throws Exception {
       // FilesResource filesResource = null;
       FilesResource filesResource = filesResourceService.downloadFile(fileId);

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(filesResource.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"filesResource;filename=\""
                + filesResource.getFilename()+"\"").body(new ByteArrayResource(filesResource.getData()));
    }

    @GetMapping("/files")

    public ResponseEntity<List<FilesModels>> getAllFiles(){
        List<FilesModels> filesModels = filesResourceImplement.getAllFiles().map(
                filesResource -> {
                    String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/download/")
                            .path(filesResource.getFileId())
                            .toUriString();
                    return new FilesModels(
                            filesResource.getFilename(),
                            downloadUrl,
                            filesResource.getFiletype(),
                            filesResource.getData().length



                    );
                }
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(filesModels);
    }
}
