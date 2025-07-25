package com.erudio.data.tdo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileDto {

    private String fileName;
    private String fileDownloadUri;
    private String fileSize;
    private String fileType;

}
