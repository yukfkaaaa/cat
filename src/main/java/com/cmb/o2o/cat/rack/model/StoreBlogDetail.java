package com.cmb.o2o.cat.rack.model;

import java.util.List;

/**
 * Created by zhaopingtao on 2019/11/29
 * Description: No Description
 */
public class StoreBlogDetail {

    private Integer id;

    private String content;

    private List<String> pics;

    private String datetime;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public List<String> getPics() {
        return pics;
    }

    public String getDatetime() {
        return datetime;
    }
}
