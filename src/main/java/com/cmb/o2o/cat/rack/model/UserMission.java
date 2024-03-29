package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class UserMission {
    private Integer id;

    private Integer missionId;

    private String userId;

    private Integer status;

    private Date finishTime;

    private Date createTime;

    public UserMission(Integer id, Integer missionId, String userId, Integer status, Date finishTime, Date createTime) {
        this.id = id;
        this.missionId = missionId;
        this.userId = userId;
        this.status = status;
        this.finishTime = finishTime;
        this.createTime = createTime;
    }

    public UserMission() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}