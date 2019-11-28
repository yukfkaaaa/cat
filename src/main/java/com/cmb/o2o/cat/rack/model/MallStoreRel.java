package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class MallStoreRel {
    private Integer id;

    private Integer mallId;

    private Integer storeId;

    private Date createTime;

    private Date updateTime;

    public MallStoreRel(Integer id, Integer mallId, Integer storeId, Date createTime, Date updateTime) {
        this.id = id;
        this.mallId = mallId;
        this.storeId = storeId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MallStoreRel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}