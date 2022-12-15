package com.moneim.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneim.api.entities.File;
import com.moneim.api.fileUpload.NginxService;
import com.moneim.api.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImplementation implements  IFileService{
    @Autowired
    NginxService nginxService;
    @Autowired
    FileRepository fileRepository;

    @Override
    public String saveFile(File file ,MultipartFile fileToUpload,String idUser) {
        String json = "";
        try {
            Object result = nginxService.uploadPicture(fileToUpload);
            json = new ObjectMapper().writeValueAsString(result);
            file.setIdUser(idUser);
            file.setDateCreation(new Date());
            file.setUrl(json);
            fileRepository.save(file);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    public List<File> findAllFilesByIdUser(String idUser) {
        return fileRepository.findByIdUser(idUser);
    }
}
