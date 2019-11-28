package com.cmb.o2o.cat.rack.model;

import java.util.Date;

public class BlogPic {
    private Integer id;

    private Integer blogId;

    private String pic;

    private Date createTime;

    private Integer status;

    public BlogPic(Integer id, Integer blogId, String pic, Date createTime, Integer status) {
        this.id = id;
        this.blogId = blogId;
        this.pic = pic;
        this.createTime = createTime;
        this.status = status;
    }

    public BlogPic() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}