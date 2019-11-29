package com.cmb.o2o.cat.rack.model;

public class Reward {
    private Integer id;

    private String name;

    private String icon;

    private Integer level;

    private Integer missionId;

    public Reward(Integer id, String name, String icon, Integer level, Integer missionId) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.level = level;
        this.missionId = missionId;
    }

    public Reward() {
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }
}