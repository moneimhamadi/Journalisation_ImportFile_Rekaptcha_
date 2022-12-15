package com.moneim.api.services;

import com.moneim.api.entities.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IFileService {
    public String saveFile(File file, MultipartFile fileToUpload,String idUser);
    public List<File> findAllFilesByIdUser(String idUser);

}
