package com.cmb.o2o.cat.rack.service;

import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QiNiuService {
    private Logger logger = LoggerFactory.getLogger(QiNiuService.class);
    private final String AK = "MYFxsSGdHbrC2-Ruv6rls56O1RVHJCsId5OQhz-X";
    private final String SK = "4Pu1eniZysTQ58aTAy2sQrlS-Dmd0GdgeieNKMRo";
    private final String BUCKET = "fkairlbs";
    private final String HOST = "http://qn.66beta.com/";

    public String upload(String file,String fileKey){
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(AK, SK);
        String upToken = auth.uploadToken(BUCKET);
        DefaultPutRet putRet = null;
        try {
            Response response = uploadManager.put(file, fileKey, upToken);
            putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
        } catch (Exception ex) {
            logger.error("asd",ex);
        }
        return HOST+putRet.key;
    }

}
