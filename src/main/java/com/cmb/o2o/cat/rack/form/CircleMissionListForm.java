package com.cmb.o2o.cat.rack.form;

/**
 * @author dragic
 * @create 2019/11/29 14:44
 */
public class CircleMissionListForm {
    /**
     * 商圈ID
     */
    private Integer mallId;

    /**
     * 用户登录后的微信openid
     */
    private String openId;

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}