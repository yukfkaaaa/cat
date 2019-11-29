package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.common.MissionStatus;
import com.cmb.o2o.cat.rack.common.StaticMap;
import com.cmb.o2o.cat.rack.dao.*;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by qiqing on 2019/11/29.
 */
@RestController
public class UserMissionController {

    @Autowired
    private UserMissionMapper userMissionMapper;

    @Autowired
    private MissionMapper missionMapper;

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    private UserRewardMapper userRewardMapper;

    @Autowired
    private UserTaskMapper userTaskMapper;

    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping("/mission/apply")
    public Response applyMission(Integer id, String openId) {


        UserMission userMission = new UserMission();

        userMission.setCreateTime(new Date());
        userMission.setMissionId(id);
        userMission.setStatus(MissionStatus.INIT);
        userMission.setUserId(openId);

        try {
            userMissionMapper.insert(userMission);
            return Response.succ();
        } catch (DuplicateKeyException e) {
            return Response.fail("您已领取该任务");
        }
    }


    @RequestMapping("/mission/apply")
    public Response applyReward(Integer missionId, String openId, Integer rewardId) {


        UserRewardExample example = new UserRewardExample();
        UserRewardExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(openId);
        criteria.andRewardIdEqualTo(rewardId);
        criteria.andMissionIdEqualTo(missionId);

        List<UserReward> list = userRewardMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            UserReward userReward = new UserReward();
            userReward.setMissionId(missionId);
            userReward.setUserId(openId);
            userReward.setRewardId(rewardId);
            userReward.setGetTime(new Date());
            userRewardMapper.insert(userReward);
            return Response.succ();
        } else {
            return Response.fail("您已领取该奖励");
        }

    }

    @RequestMapping("/mission/finishTask")
    public Response finishMissionTask(String openId, Integer taskId, Integer missionId) {



        return Response.succ();

    }
}
