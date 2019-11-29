package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class UserTask {
    private Integer id;

    private Integer taskId;

    private String userId;

    private Date finishTime;

    public UserTask(Integer id, Integer taskId, String userId, Date finishTime) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.finishTime = finishTime;
    }

    public UserTask() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}