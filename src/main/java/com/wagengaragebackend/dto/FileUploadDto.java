package com.wagengaragebackend.dto;

import com.wagengaragebackend.data.FileUpload;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {

    private String title;

    private String description;

    private String fileName;

    private String mediaType;

    private String downloadUri;


    public FileUploadDto() {
    }


    public static FileUploadDto fromFileUpload(FileUpload fileUpload){
        FileUploadDto Dto = new FileUploadDto();
        Dto.setTitle(fileUpload.getTitle());
        Dto.setDescription(fileUpload.getDescription());
        Dto.setFileName(fileUpload.getFileName());
        Dto.setMediaType(fileUpload.getMediaType());
        return Dto;
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

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
