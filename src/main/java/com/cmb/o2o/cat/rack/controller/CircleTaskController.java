package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dao.TaskMapper;
import com.cmb.o2o.cat.rack.dao.UserTaskMapper;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.form.CircleMissionListForm;
import com.cmb.o2o.cat.rack.model.*;
import com.cmb.o2o.cat.rack.service.CircleMissionService;
import com.cmb.o2o.cat.rack.vo.MissionVo;
import com.cmb.o2o.cat.rack.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dragic
 * @create 2019/11/29 14:56
 */
@RestController
public class CircleTaskController {
    @Autowired
    private CircleMissionService service;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserTaskMapper userTaskMapper;


    private List<Task> getTaskList(Integer missionId){

        TaskExample taskExample=new TaskExample();
        TaskExample.Criteria criteria=taskExample.createCriteria();
        criteria.andMissionIdEqualTo(missionId);

        return taskMapper.selectByExample(taskExample);

    }



    @RequestMapping("/mission/list")
    public Response missionList(CircleMissionListForm form)  {
        String openid = form.getOpenid();
        Integer mallId = form.getMallId();
        List<Mission> missions = service.getMissionListByMallId(mallId);
        List<MissionVo> missionVoList=new ArrayList<>(missions.size());

        for (Mission mission : missions) {
            Integer missionId = mission.getId();
            List<UserMission> userMissions = service.getUserMissionByUserId(openid, missionId);
            MissionVo missionVo=new MissionVo();
            missionVo.setId(mission.getId());
            missionVo.setTitle(mission.getTitle());

            List<Task> taskList=getTaskList(missionId);

            missionVo.setTasks(taskList.stream().map(t->{
                TaskVo taskVo=new TaskVo();
                taskVo.setStatus(0);
                taskVo.setId(t.getId());
                taskVo.setName(t.getName());
                return taskVo;
            }).collect(Collectors.toList()));

            if(CollectionUtils.isEmpty(userMissions)){
                missionVo.setStatus(0);
                missionVo.setHasUnclaimedReward(0);

            }else {
                missionVo.setStatus(1);
                List<UserTask> userTaskList=userTaskMapper.getCompleteTaskByMissionIdByUserId(openid,missionId);

                for(TaskVo taskVo:missionVo.getTasks()){
                    for(UserTask userTask:userTaskList){
                        if(taskVo.getId().equals(userTask.getId())){
                            taskVo.setStatus(1);
                        }
                    }
                }
            }

            missionVoList.add(missionVo);

        }

        return Response.succ(missionVoList);
    }
}
