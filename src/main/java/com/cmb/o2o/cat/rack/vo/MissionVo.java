package com.cmb.o2o.cat.rack.vo;

import java.util.List;

/**
 * @author dragic
 * @create 2019/11/29 16:34
 */
public class MissionVo {
    /**
     * mission id
     */
    private Integer id;

    /**
     * mission标题
     */
    private String title;

    /**
     * 子任务列表
     */
    private List<TaskVo> taskVos;

    /**
     * 是否有奖励可以领取
     */
    private Integer hasUnclaimedReward;

    /**
     * 领取状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TaskVo> getTaskVos() {
        return taskVos;
    }

    public void setTaskVos(List<TaskVo> taskVos) {
        this.taskVos = taskVos;
    }

    public Integer getHasUnclaimedReward() {
        return hasUnclaimedReward;
    }

    public void setHasUnclaimedReward(Integer hasUnclaimedReward) {
        this.hasUnclaimedReward = hasUnclaimedReward;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}