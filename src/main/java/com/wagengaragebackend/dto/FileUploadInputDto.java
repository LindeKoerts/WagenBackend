package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.FileUpload;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadInputDto {


    private String fileName;

    private String title;

    private String description;

    private MultipartFile file;

    private String mediaType;

    public FileUpload toFileUpload(FileUploadInputDto Dto){
        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(Dto.getFileName());
        fileUpload.setTitle(Dto.getTitle());
        fileUpload.setDescription(Dto.getDescription());
        fileUpload.setMediaType(Dto.getMediaType());
        return fileUpload;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
