package com.cmb.o2o.cat.rack.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.dao.*;
import com.cmb.o2o.cat.rack.form.MissionConsoleForm;
import com.cmb.o2o.cat.rack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private DistrictMapper districtMapper;

    @Autowired
    private RewardMapper rewardMapper;

    @Autowired
    private StoreMapper storeMapper;

    public List<Mission> getMissionListByMallId(Integer mallId) {
        List<Mission> missions = missionMapper.selectByDistrictId(mallId);
        return missions;
    }

    public List<UserMission> getUserMissionByUserId(String userId, Integer missionId) {
        List<UserMission> userMissions =  userMissionMapper.selectByUserIdAndMissionId(userId, missionId);
        return userMissions;
    }
    /*查询商圈任务列表*/
    public Map<String,Object> queryMissionsByMall(MissionConsoleForm form){
        Integer districtId = form.getMallId();
        District district = districtMapper.selectByPrimaryKey(districtId);
        List<Mission> missions = missionMapper.selectByDistrictId(districtId);
        RewardExample rewardExample = new RewardExample();
        RewardExample.Criteria cri =  rewardExample.createCriteria();
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria tas = taskExample.createCriteria();
        for (Mission mission : missions){
            cri.andMissionIdEqualTo(mission.getId());
            tas.andMissionIdEqualTo(mission.getId());
            List<Reward> rewards = rewardMapper.selectByExample(rewardExample);
            List<String> temp = new ArrayList<>();
            for (Reward reward : rewards){
                temp.add(reward.getName());
            }
            List<Task> tasks = taskMapper.selectByExample(taskExample);
            mission.setTasks(tasks);
            mission.setRewardList(temp);
        }
        JSONObject res = new JSONObject();
        res.put("mall",district);
        res.put("missions",missions);
        return res;
    }


    /*新增或编辑任务*/
    public List<Map<String,Object>> addOrUpdateMission(MissionConsoleForm form){
        int missionId = 0;
        if(form.getMissionId() != null && form.getMissionId()>0){
            missionId = form.getMissionId();
//            missionMapper.deleteByPrimaryKey(missionId);
            taskMapper.deleteByMissionId(missionId);
            rewardMapper.deleteByMissionId(missionId);
        }
        Integer districtId = form.getMallId();
        String[] storeIds = form.getStoreIds().split(",");
        String missionTitle = form.getMissionType();

        if(missionId==0){
            Mission mission = new Mission();
            mission.setCreateTime(new Date());
            mission.setExpireTime(new Date());
            mission.setDistrictId(districtId);
            mission.setTitle(missionTitle);
            missionMapper.insertSelective(mission);
            missionId = mission.getId();
        }

        for(String storeId : storeIds){
            Store store = storeMapper.selectByPrimaryKey(Integer.valueOf(storeId));
            Task task = new Task();
            task.setMissionId(missionId);
            task.setStoreId(Integer.valueOf(storeId));
            task.setName(store.getName());
            taskMapper.insert(task);
        }

        String rewardsDesc = form.getRewardsDesc();
        if(!org.springframework.util.StringUtils.isEmpty(rewardsDesc)){
            String[] rewards = rewardsDesc.split(",");
            for (int i=0; i<rewards.length;i++){
                Reward reward = new Reward();
                reward.setName(rewards[i]);
                reward.setMissionId(missionId);
                reward.setLevel(i+1);

                rewardMapper.insert(reward);
            }
        }
        return null;
    }

    public int  delMission(MissionConsoleForm form) {
        return missionMapper.deleteByPrimaryKey(form.getMissionId());
    }
}
