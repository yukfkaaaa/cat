package com.cmb.o2o.cat.rack.model;

public class Task {
    private Integer id;

    private String name;

    private Integer missionId;

    private Integer storeId;

    private String desc;

    public Task(Integer id, String name, Integer missionId, Integer storeId, String desc) {
        this.id = id;
        this.name = name;
        this.missionId = missionId;
        this.storeId = storeId;
        this.desc = desc;
    }

    public Task() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}