package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.form.CircleMissionListForm;
import com.cmb.o2o.cat.rack.model.Mission;
import com.cmb.o2o.cat.rack.model.Task;
import com.cmb.o2o.cat.rack.model.UserMission;
import com.cmb.o2o.cat.rack.model.UserTask;
import com.cmb.o2o.cat.rack.service.CircleMissionService;
import com.cmb.o2o.cat.rack.vo.MissionVo;
import com.cmb.o2o.cat.rack.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dragic
 * @create 2019/11/29 14:56
 */
@RestController
public class CircleTaskController {
    @Autowired
    private CircleMissionService service;

    @RequestMapping("/mission/list")
    public Response missionList(CircleMissionListForm form)  {
        String openid = form.getOpenid();
        Integer mallId = form.getMallId();
        List<Mission> missions = service.getMissionListByMallId(mallId);
        for (Mission mission : missions) {
            Integer missionId = mission.getId();
            List<UserMission> userMissions = service.getUserMissionByUserId(openid, missionId);
            List<MissionVo> missionVoList = new ArrayList<>(16);
            for (UserMission userMission : userMissions) {
                MissionVo missionVo = new MissionVo();
                if(CollectionUtils.isEmpty(userMissions)) {
                    missionVo.setStatus(0);
                } else {
                    missionVo.setStatus(1);
                }
                missionVo.setId(mission.getId());
                missionVo.setTitle(mission.getTitle());
                Integer myMissionId = userMission.getMissionId();
                List<Task> myTasks = service.getTaskByMissionId(myMissionId);
                List<TaskVo> taskVoList = new ArrayList<>(myTasks.size());
                for (Task task : myTasks) {
                    TaskVo taskVo = new TaskVo();
                    Integer myTaskId = task.getId();
                    List<UserTask> completeTasks = service.getCompleteTask(openid, myTaskId);
                    taskVo.setId(myTaskId);
                    taskVo.setName(task.getName());
                    if(CollectionUtils.isEmpty(completeTasks)) {
                        taskVo.setStatus(0);
                    } else {
                        taskVo.setStatus(1);
                    }
                    taskVoList.add(taskVo);
                }
                missionVo.setTaskVos(taskVoList);


                missionVoList.add(missionVo);
            }


        }

        return Response.succ();
    }
}
