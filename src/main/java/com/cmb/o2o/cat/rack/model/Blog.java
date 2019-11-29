package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class Blog {
    private Integer id;

    private Integer storeId;

    private String text;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer spStatus;

    private Integer reportNum;

    private String msg;

    public Blog(Integer id, Integer storeId, String text, Integer status, Date createTime, Date updateTime, Integer spStatus, Integer reportNum, String msg) {
        this.id = id;
        this.storeId = storeId;
        this.text = text;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.spStatus = spStatus;
        this.reportNum = reportNum;
        this.msg = msg;
    }

    public Blog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }
}