package com.cmb.o2o.cat.rack.model;

public class BlogLike {
    private Integer id;

    private String userId;

    private Integer blogId;

    public BlogLike(Integer id, String userId, Integer blogId) {
        this.id = id;
        this.userId = userId;
        this.blogId = blogId;
    }

    public BlogLike() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}