package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class BudgetDetail {
    private Long id;

    private Long planId;

    private Long activityId;

    private String payOrderNo;

    private Integer bizOrderNo;

    private String passuid;

    private Integer amount;

    private Integer point;

    private Integer ticketCount;

    private String direction;

    private Boolean valid;

    private String type;

    private Date createdTime;

    private Date updatedTime;

    public BudgetDetail(Long id, Long planId, Long activityId, String payOrderNo, Integer bizOrderNo, String passuid, Integer amount, Integer point, Integer ticketCount, String direction, Boolean valid, String type, Date createdTime, Date updatedTime) {
        this.id = id;
        this.planId = planId;
        this.activityId = activityId;
        this.payOrderNo = payOrderNo;
        this.bizOrderNo = bizOrderNo;
        this.passuid = passuid;
        this.amount = amount;
        this.point = point;
        this.ticketCount = ticketCount;
        this.direction = direction;
        this.valid = valid;
        this.type = type;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public BudgetDetail() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getPayOrderNo() {
        return payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo == null ? null : payOrderNo.trim();
    }

    public Integer getBizOrderNo() {
        return bizOrderNo;
    }

    public void setBizOrderNo(Integer bizOrderNo) {
        this.bizOrderNo = bizOrderNo;
    }

    public String getPassuid() {
        return passuid;
    }

    public void setPassuid(String passuid) {
        this.passuid = passuid == null ? null : passuid.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}