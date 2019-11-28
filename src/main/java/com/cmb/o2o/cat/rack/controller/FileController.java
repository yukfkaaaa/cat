package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.service.BlogService;
import com.cmb.o2o.cat.rack.service.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/file")
public class FileController {
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileUploader fileUploader;
    @Autowired
    private BlogService blogService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Response uploadPic(HttpServletRequest request){
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        if(CollectionUtils.isEmpty(fileMap)){
            return Response.fail("没有文件上传");
        }

        List<String> fileList = new ArrayList<>();
        Iterator<Map.Entry<String,MultipartFile>> iterator = fileMap.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,MultipartFile> entry = iterator.next();
            MultipartFile file = entry.getValue();
            String basePath = mRequest.getSession().getServletContext().getContextPath()+"/static/pic/";
            String fileUrl = null;
            try {
                fileUrl = fileUploader.uploadPic(basePath,file);
                fileList.add(fileUrl);
            } catch (IOException e) {
                logger.error("上传失败",e);
                fileUploader.deletePic(fileList);
                return Response.fail("上传失败");
            }
        }

        List<Integer> ids = blogService.savePics(fileList);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("ids",ids);
        return Response.succ(retMap);
    }
}
