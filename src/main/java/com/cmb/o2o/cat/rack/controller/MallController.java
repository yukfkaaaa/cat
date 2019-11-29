package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.model.StoreBlog;
import com.cmb.o2o.cat.rack.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MallController {

    @Autowired
    private MallService mallService;

    @RequestMapping(value="mallDynamics")
    public Response mallDynamics(Integer mallId){
        System.out.println("first mallId="+mallId);
        List<StoreBlog> list =  mallService.queryMallDynamics(mallId);
        Map<String, Object> retMap = new HashMap();
        System.out.println("list="+ JSONObject.toJSONString(list));
        retMap.put("activityList", list);
        return Response.succ(retMap);
    }

}
