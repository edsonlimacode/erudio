package com.erudio.controller;

import com.erudio.data.tdo.UploadFileDto;
import com.erudio.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload/single")
    public UploadFileDto upload(@RequestParam("file") MultipartFile file) {

        var fileName = fileUploadService.upload(file);

        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download")
                .path(fileName)
                .toUriString();

        return new UploadFileDto(fileName, fileDownloadUri, file.getSize(), file.getContentType());
    }

    @PostMapping("/upload/multi")
    public ResponseEntity<List<UploadFileDto>> uploadFiles(MultipartFile file) {
        return ResponseEntity.ok(List.of());
    }

    @GetMapping("/download")
    public ResponseEntity<?> downlaod() {
        return null;
    }

}
