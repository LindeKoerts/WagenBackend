package com.wagengaragebackend.repository;

import com.wagengaragebackend.data.FileUpload;
import org.springframework.data.repository.CrudRepository;

public interface FileUploadRepository extends CrudRepository<FileUpload,  Long> {
}
