package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class Mission {
    private Integer id;

    private String title;

    private Date createTime;

    private Integer districtId;

    private Date expireTime;

    public Mission(Integer id, String title, Date createTime, Integer districtId, Date expireTime) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.districtId = districtId;
        this.expireTime = expireTime;
    }

    public Mission() {
        super();
    }

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
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}