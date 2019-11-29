package com.cmb.o2o.cat.rack.form;

/**
 * Created by ASUS on 2019/11/29.
 */
public class MissionConsoleForm {
    private Integer mallId;
    private Integer missionId;
    private String missionType;
    private String storeIds;
    private String cityName;
    private String mallName;
    private String rewardIds;
    private String rewardsDesc;
    private String missionExpireDate;

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public Integer getMissionId() {
        return missionId;
    }

    public void setMissionId(Integer missionId) {
        this.missionId = missionId;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getRewardIds() {
        return rewardIds;
    }

    public void setRewardIds(String rewardIds) {
        this.rewardIds = rewardIds;
    }

    public String getRewardsDesc() {
        return rewardsDesc;
    }

    public void setRewardsDesc(String rewardsDesc) {
        this.rewardsDesc = rewardsDesc;
    }

    public String getMissionExpireDate() {
        return missionExpireDate;
    }

    public void setMissionExpireDate(String missionExpireDate) {
        this.missionExpireDate = missionExpireDate;
    }
}
