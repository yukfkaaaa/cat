package com.cmb.o2o.cat.rack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class FileUploader {
    private final String BASE_FILE_PATH =
            this.getClass().getClassLoader().getResource("application.properties").getPath();
    private final String BASE_DIR = "/static/pic/";

    @Autowired
    private QiNiuService qiNiuService;

    public String uploadPic(MultipartFile file) throws IOException {
        String oriFileName = file.getOriginalFilename();
        String picType = getFileExtension(oriFileName);
        String newName = System.currentTimeMillis()+"."+picType;


        File baseFile = new File(BASE_FILE_PATH);
        String newPath = baseFile.getParent()+BASE_DIR;
        File newFile = new File(newPath);
        if(!newFile.exists()){
            newFile.mkdirs();
        }
        String newFilePath = newPath+newName;
        FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(newFilePath));

        String returnKey = qiNiuService.upload(newFilePath,newName);

        File newPic = new File(newFilePath);
        if(newPic.exists() && newPic.isFile()){
            newPic.delete();
        }
        return returnKey;
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
            return "jpg";
        }
    }

}
