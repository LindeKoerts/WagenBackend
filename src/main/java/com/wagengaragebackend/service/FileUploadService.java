package com.wagengaragebackend.service;

import com.wagengaragebackend.data.FileUpload;
import com.wagengaragebackend.dto.FileUploadDto;
import com.wagengaragebackend.dto.FileUploadInputDto;
import com.wagengaragebackend.exception.FileStorageException;
import com.wagengaragebackend.exception.RecordNotFoundException;
import com.wagengaragebackend.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class FileUploadService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");

    private FileUploadRepository fileUploadRepository;

    @Autowired
    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }

    //?  alléén toepassen eerste maal ,wanneer geen uploads-folder aanwezig (in uploadFile)
    public void init() {
        try {
            Files.createDirectory(uploads);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public Iterable<FileUpload> getFiles() {
        return fileUploadRepository.findAll();
    }

    public FileUploadDto getFileById(long id) {
        Optional<FileUpload> stored = fileUploadRepository.findById(id);

        if (stored.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand("download").toUri();

            FileUpload fileUpload =stored.get();
            FileUploadDto fileUploadDto = FileUploadDto.fromFileUpload(fileUpload);
            fileUploadDto.setDownloadUri(uri.toString());
            return fileUploadDto;
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    //?  public boolean fileExistsById(long id) {
    //      return fileUploadRepository.existsById(id);
    //   }

    public Resource downloadFile(long id) {
        Optional<FileUpload> stored = fileUploadRepository.findById(id);

        if (stored.isPresent()) {
            String fileName = stored.get().getFileName();
            Path path = this.uploads.resolve(fileName);

            Resource resource = null;
            try {
                resource = new UrlResource(path.toUri());
                return resource;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new RecordNotFoundException(); }
        return null;
    }

    public long uploadFile(FileUploadInputDto dto) {

        MultipartFile file = dto.getFile();
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path copyLocation = this.uploads.resolve(file.getOriginalFilename());

        try {
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileStorageException("Could not store file " + originalFilename + ". Please try again!");
        }

        FileUpload newFile = new FileUpload();
        newFile = dto.toFileUpload(dto);
        newFile.setLocation(copyLocation.toString());

        FileUpload storedFile = fileUploadRepository.save(newFile);

        return storedFile.getId();
    }

    public void deleteFile(long id) {
        Optional<FileUpload> stored = fileUploadRepository.findById(id);

        if (stored.isPresent()) {
            String filename = stored.get().getFileName();
            Path location = this.uploads.resolve(filename);
            try {
                Files.deleteIfExists(location);
            }
            catch (IOException ex) {
                throw new RuntimeException("File not found");
            }

            fileUploadRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
        }
    }
}

