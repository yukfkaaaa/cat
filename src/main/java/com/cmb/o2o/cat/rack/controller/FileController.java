package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping("/pic/upload")
    @ResponseBody
    public Response uploadPic(HttpServletRequest request){
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mRequest.getFileMap();
        if(CollectionUtils.isEmpty(fileMap)){
            return Response.fail("没有文件上传");
        }
        Iterator<Map.Entry<String,MultipartFile>> iterator = fileMap.entrySet().iterator();
        while(iterator.hasNext()){
            MultipartFile
        }

    }
}
