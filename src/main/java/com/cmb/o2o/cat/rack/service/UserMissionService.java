package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.common.MissionStatus;
import com.cmb.o2o.cat.rack.common.StaticMap;
import com.cmb.o2o.cat.rack.dao.*;
import com.cmb.o2o.cat.rack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by qiqing on 2019/11/29.
 */
@Service
public class UserMissionService {

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


    public void joinMission(Integer missionId,String  openId){
        UserMission userMission = new UserMission();

        userMission.setCreateTime(new Date());
        userMission.setMissionId(missionId);
        userMission.setStatus(MissionStatus.INIT);
        userMission.setUserId(openId);

        userMissionMapper.insert(userMission);
    }

    public boolean  getReward(Integer missionId, String openId, Integer rewardId){

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
            int num=getUserNotGetMissionReward(openId,String.valueOf(missionId));
            if(num>1){
                cacheUserNotGetMissionReward(openId,String.valueOf(missionId),num-1);
            }else {
                StaticMap.delete(openId+"_"+missionId);
            }
            return true;
        }

        return false;


    }

    @Transactional(rollbackFor = Throwable.class)
    public int finishMissionTask(String openId,Integer taskId,Integer missionId){
        UserTask userTask = new UserTask();
        userTask.setUserId(openId);
        userTask.setTaskId(taskId);
        userTask.setFinishTime(new Date());
        userTaskMapper.insert(userTask);

        //mission下的任务列表
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria taskCriteria = taskExample.createCriteria();
        taskCriteria.andMissionIdEqualTo(missionId);
        List<Task> taskList = taskMapper.selectByExample(taskExample);

        //mission下的奖励
        RewardExample rewardExample = new RewardExample();
        RewardExample.Criteria criteria = rewardExample.createCriteria();
        criteria.andMissionIdEqualTo(missionId);
        List<Reward> rewards = rewardMapper.selectByExample(rewardExample);


        //用户已完成列表
        UserTaskExample userTaskExample = new UserTaskExample();
        UserTaskExample.Criteria userTaskCriteria = userTaskExample.createCriteria();
        userTaskCriteria.andUserIdEqualTo(openId);
        userTaskCriteria.andTaskIdIn(taskList.stream().map(t -> t.getId()).collect(Collectors.toList()));

        List<UserTask> finishTaskList = userTaskMapper.selectByExample(userTaskExample);

        int finishNum = finishTaskList.size();

        UserRewardExample userRewardExample = new UserRewardExample();
        UserRewardExample.Criteria userRewardCriteria = userRewardExample.createCriteria();

        userRewardCriteria.andMissionIdEqualTo(missionId);
        userRewardCriteria.andUserIdEqualTo(openId);

        List<UserReward> userRewardList = userRewardMapper.selectByExample(userRewardExample);
        Set<Integer> getRewardIds = userRewardList.stream().map(r -> r.getRewardId()).collect(Collectors.toSet());

        List<Reward> notGetRewardList = rewards.stream().filter(r -> r.getLevel() <= finishNum && !getRewardIds.contains(r.getId())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(notGetRewardList)) {
            StaticMap.put(openId+"_"+missionId,notGetRewardList.size());
        }
        return notGetRewardList.size();
    }


    private   void cacheUserNotGetMissionReward(String openId,String missionId,int rewardNum){
        StaticMap.put(openId+"_"+missionId,rewardNum);
    }

    private int getUserNotGetMissionReward(String openId,String missionId){
        Integer num=(Integer) StaticMap.get(openId+"_"+missionId);
        if(num==null){
            return 0;
        }
        return num;

    }

}
