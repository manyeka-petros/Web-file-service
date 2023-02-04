package com.mapoto.Files.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilesModels {
    private String filename;
    private String downloadUrl;
    private String filetype;
    private long filesize;


}
