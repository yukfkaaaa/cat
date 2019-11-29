package com.cmb.o2o.cat.rack.service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileUploader {
    private final String BASE_DIR = "/home/appsvr/upload/pic/";

    public String uploadPic(String basePath,MultipartFile file) throws IOException {
        String oriFileName = file.getOriginalFilename();
        String picType = getFileExtension(oriFileName);
        String newName = basePath+System.currentTimeMillis()+"."+picType;
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(newName));
        return newName;
    }

    public String deletePic(List<String> pics){
        for(String pic : pics){
            File file = new File(pic);
            if(file.exists() && file.isFile()){
                file.delete();
            }
        }
        return null;
    }

    private String getFileExtension(String fileName){
        int idx = fileName.lastIndexOf('.');
        if(idx > -1){
            return fileName.substring(idx +1 ,fileName.length());
        }else {
            return null;
        }
    }

}
