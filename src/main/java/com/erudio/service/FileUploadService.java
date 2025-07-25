package com.erudio.service;

import com.erudio.config.FileStorageConfig;
import com.erudio.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileUploadService {

    private Path fileLocation;

    @Autowired
    public FileUploadService(FileStorageConfig fileConfig) {

        fileLocation = Paths.get(fileConfig.getUploadDir())
                .toAbsolutePath().toAbsolutePath()
                .normalize();

        try {
            Files.createDirectories(fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("Não foi possivel criar o diretorio", e);
        }
    }

    public String upload(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileUploadException("Não foi possivel criar o diretorio");
            }

            Path targetLocation = fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new FileUploadException("Não foi possivel criar o diretorio", e);
        }
    }
}
