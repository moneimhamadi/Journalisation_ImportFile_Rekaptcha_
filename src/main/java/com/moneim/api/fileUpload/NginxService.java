package com.moneim.api.fileUpload;

import org.springframework.web.multipart.MultipartFile;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class NginxService {

    public Object uploadPicture(MultipartFile uploadFile) {
        //1. Generate a new file name for the uploaded image
        //1.1 get the original file name
        String oldName = uploadFile.getOriginalFilename();
        //1.2 use the idutils tool class to generate a new file name. The new file name = newname + file suffix
        String newName = IDUtils.genImageName();
        assert oldName != null;
        newName = newName + oldName.substring(oldName.lastIndexOf("."));
        //1.3 subdirectories of generated files stored on the server side
        String filePath = new DateTime().toString("/yyyyMMdd/");
        //2. Upload the picture to the picture server
        //2.1 get the uploaded IO stream
        InputStream input = null;
        try {
            input = uploadFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.2 call the ftutil tool class to upload
        return FtpUtil.putImages(input, filePath, newName);
    }
}
