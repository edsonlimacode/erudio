package com.erudio.data.tdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadFileDto {

    private String fileName;
    private String fileDownloadUri;
    private long fileSize;
    private String fileType;

}
