package com.cmb.o2o.cat.rack.controller;

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
        List<StoreBlog> list =  mallService.queryMallDynamics(mallId);
        Map<String, Object> retMap = new HashMap();
        retMap.put("activityList", list);
        return Response.succ(retMap);
    }

}
