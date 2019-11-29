package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.dao.*;
import com.cmb.o2o.cat.rack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dragic
 * @create 2019/11/29 15:04
 */
@Service
public class CircleMissionService {
    @Autowired
    private MissionMapper missionMapper;

    @Autowired
    private UserMissionMapper userMissionMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private UserTaskMapper userTaskMapper;

    @Autowired
    private UserRewardMapper userRewardMapper;

    public List<Mission> getMissionListByMallId(Integer mallId) {
        List<Mission> missions = missionMapper.selectByDistrictId(mallId);
        return missions;
    }

    public List<UserMission> getUserMissionByUserId(String userId, Integer missionId) {
        List<UserMission> userMissions =  userMissionMapper.selectByUserIdAndMissionId(userId, missionId);
        return userMissions;
    }

    public List<Task> getTaskByMissionId(Integer missionId) {
        List<Task> tasks = taskMapper.getTaskByMissionId(missionId);
        return tasks;
    }
}
