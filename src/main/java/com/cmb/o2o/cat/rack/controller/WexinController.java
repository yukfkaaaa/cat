package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.common.HttpClientUtil;
import com.cmb.o2o.cat.rack.common.StaticMap;
import com.cmb.o2o.cat.rack.dto.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * Created by qiqing on 2019/11/29.
 */
@RestController
public class WexinController {

    private static final String appId="wx3644efdfc58ccfc8";

    private static final String appSecret="be5b4f9b63091967f97e4309360e96ec";

    private static final String SUCCESS="0";

    private static final String GET_SESSION_URL="http://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=JSCODE&grant_type=authorization_code&connect_redirect=1";

    @RequestMapping("/wx/login")
    public Response login(String code){

        try {
            String resp=HttpClientUtil.get(String.format(GET_SESSION_URL, appId, appSecret).replaceAll("JSCODE",code));
            JSONObject jsonObject= JSON.parseObject(resp);
            String errorCode=jsonObject.getString("errcode");
            if(SUCCESS.equals(errorCode)){

                String openId=jsonObject.getString("openid");
                String sessionkey=jsonObject.getString("session_key");
                StaticMap.put(openId,sessionkey);
                return Response.succ(openId);
            }else {
                return Response.fail(jsonObject.getString("errmsg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.fail("请重试");
        }
    }
}
