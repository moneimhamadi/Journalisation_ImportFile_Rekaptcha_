package com.moneim.api.Endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneim.api.entities.File;
import com.moneim.api.fileUpload.NginxService;
import com.moneim.api.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/file")
public class FileEndpoint {
    @Autowired
    private NginxService nginxService;
    @Autowired
    IFileService fileService;

    @PostMapping("/upload")
    public String pictureUpload(@RequestParam(value = "file") MultipartFile uploadFile) {
        String json = "";
        try {
            Object result = nginxService.uploadPicture(uploadFile);
            json = new ObjectMapper().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @PostMapping("/uploadFile/{idUser}")
    public String uploadFile(@RequestParam(value = "fileToUpload") MultipartFile fileToUpload,
                             @RequestPart (value = "file")File file,
                             @PathVariable (value = "idUser")String idUser) {
        return fileService.saveFile(file,fileToUpload,idUser);
    }

    @GetMapping("/findAllByIdUser/{idUser}")
    public List<File> findAllByIdUser(@PathVariable String idUser){
        return  fileService.findAllFilesByIdUser(idUser);
    }

    

}