package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dao.*;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.form.CircleMissionListForm;
import com.cmb.o2o.cat.rack.model.*;
import com.cmb.o2o.cat.rack.service.CircleMissionService;
import com.cmb.o2o.cat.rack.service.UserMissionService;
import com.cmb.o2o.cat.rack.vo.MissionVo;
import com.cmb.o2o.cat.rack.vo.RewardVO;
import com.cmb.o2o.cat.rack.vo.StoreVO;
import com.cmb.o2o.cat.rack.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private MissionMapper missionMapper;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private UserRewardMapper userRewardMapper;

    @Autowired
    private RewardMapper rewardMapper;


    private List<Task> getTaskList(Integer missionId){

        TaskExample taskExample=new TaskExample();
        TaskExample.Criteria criteria=taskExample.createCriteria();
        criteria.andMissionIdEqualTo(missionId);

        return taskMapper.selectByExample(taskExample);

    }


    private MissionVo getMissionVo(Mission mission,String openId){
        List<UserMission> userMissions = service.getUserMissionByUserId(openId, mission.getId());
        MissionVo missionVo=new MissionVo();
        missionVo.setId(mission.getId());
        missionVo.setTitle(mission.getTitle());

        List<Task> taskList=getTaskList(mission.getId());

        missionVo.setTasks(taskList.stream().map(t->{
            TaskVo taskVo=new TaskVo();
            taskVo.setStatus(0);
            taskVo.setId(t.getId());
            taskVo.setName(t.getName());
            taskVo.setStoreId(t.getStoreId());
            return taskVo;
        }).collect(Collectors.toList()));

        if(CollectionUtils.isEmpty(userMissions)){
            missionVo.setStatus(0);
            missionVo.setHasUnclaimedReward(0);

        }else {
            missionVo.setStatus(1);
            List<UserTask> userTaskList=userTaskMapper.getCompleteTaskByMissionIdByUserId(openId,mission.getId());

            for(TaskVo taskVo:missionVo.getTasks()){
                for(UserTask userTask:userTaskList){
                    if(taskVo.getId().equals(userTask.getTaskId())){
                        taskVo.setStatus(1);
                    }
                }
            }
        }
        return missionVo;

    }

    @RequestMapping("/mission/list")
    public Response missionList(CircleMissionListForm form)  {
        String openid = form.getOpenId();
        Integer mallId = form.getMallId();
        List<Mission> missions = service.getMissionListByMallId(mallId);
        List<MissionVo> missionVoList=new ArrayList<>(missions.size());

        for (Mission mission : missions) {
            MissionVo missionVo=getMissionVo(mission,openid);

            missionVoList.add(missionVo);

        }

        return Response.succ(missionVoList);
    }

    @RequestMapping("/mission/detail")
    public Response missionDetail(Integer missionId,String openId)  {

        Mission mission=missionMapper.selectByPrimaryKey(missionId);

        if(mission==null){
            return Response.fail("任务不存在");
        }

        MissionVo missionVo=getMissionVo(mission,openId);

        List<TaskVo> taskVoList=missionVo.getTasks();
        List<StoreVO> storeVoList=new ArrayList<>(taskVoList.size());
        for(TaskVo taskVo:taskVoList){
            Store store=storeMapper.selectByPrimaryKey(taskVo.getStoreId());
            StoreVO storeVo=new StoreVO();
            storeVo.setId(store.getId());
            storeVo.setName(store.getName());
            storeVo.setDesc(store.getAddress());
            storeVo.setDistance(300);
            storeVoList.add(storeVo);
        }
        missionVo.setStores(storeVoList);

        UserRewardExample example = new UserRewardExample();
        UserRewardExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(openId);
        criteria.andMissionIdEqualTo(missionId);

        List<UserReward> userRewardList = userRewardMapper.selectByExample(example);
        Set<Integer> getRewardIds = userRewardList.stream().map(r -> r.getRewardId()).collect(Collectors.toSet());


        //mission下的奖励
        RewardExample rewardExample = new RewardExample();
        RewardExample.Criteria rewardExampleCriteria = rewardExample.createCriteria();
        rewardExampleCriteria.andMissionIdEqualTo(missionId);
        List<Reward> rewards = rewardMapper.selectByExample(rewardExample);

        int finishNum=missionVo.getTasks().stream().filter(t->t.getStatus()==1).collect(Collectors.toList()).size();
        List<RewardVO> rewardVOList=new ArrayList<>(rewards.size());
        for(Reward reward:rewards){
            RewardVO rewardVo=new RewardVO();
            rewardVo.setName(reward.getName());
            rewardVo.setId(reward.getId());
            rewardVo.setStatus(0);
            if(getRewardIds.contains(reward.getId())){
                rewardVo.setStatus(2);
            }else if(reward.getLevel()<=finishNum) {
                rewardVo.setStatus(1);
            }
            rewardVOList.add(rewardVo);
        }
        missionVo.setRewards(rewardVOList);

        return Response.succ(missionVo);
    }
}
