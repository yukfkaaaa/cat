package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class UserReward {
    private Integer id;

    private Integer missionId;

    private Integer rewardId;

    private String userId;

    private Date getTime;

    public UserReward(Integer id, Integer missionId, Integer rewardId, String userId, Date getTime) {
        this.id = id;
        this.missionId = missionId;
        this.rewardId = rewardId;
        this.userId = userId;
        this.getTime = getTime;
    }

    public UserReward() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }
}