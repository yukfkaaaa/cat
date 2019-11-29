package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.form.MissionConsoleForm;
import com.cmb.o2o.cat.rack.service.CircleMissionService;
import com.cmb.o2o.cat.rack.service.MallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2019/11/29.
 */

@RestController
public class MissionConsoleController {

    @Autowired
    MallService mallService;

    @Autowired
    CircleMissionService circleMissionService;

    @RequestMapping(value="console/queryMalls")
    public Response queryMalls(MissionConsoleForm form){
        return Response.succ(mallService.queryMalls(form));
    }

    @RequestMapping(value="console/queryMissionsByMall")
    public Response queryMissionsByMall(MissionConsoleForm form){
        return Response.succ(circleMissionService.queryMissionsByMall(form));
    }

    @RequestMapping(value="console/addOrUpdateMission")
    public Response addOrUpdateMission(MissionConsoleForm form){
        return Response.succ(circleMissionService.addOrUpdateMission(form));
    }

    @RequestMapping(value="console/delMission")
    public Response delMission(MissionConsoleForm form){
        return Response.succ(circleMissionService.delMission(form));
    }

}
