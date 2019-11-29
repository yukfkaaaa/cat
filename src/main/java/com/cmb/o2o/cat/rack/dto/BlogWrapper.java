package com.cmb.o2o.cat.rack.dto;

import com.cmb.o2o.cat.rack.model.Blog;
import com.cmb.o2o.cat.rack.model.BlogPic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BlogWrapper {
    private String id;
    private String text;
    private Integer status;
    private String time;
    private List<String> pics;

    public BlogWrapper(Blog blog, List<BlogPic> pics){
        this.id = String.valueOf(blog.getId());
        this.text = blog.getText();
        this.status = blog.getStatus();
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        this.time = formatter.format(blog.getUpdateTime());
        this.pics = new ArrayList<>(pics.size());
        for(BlogPic blogPic : pics){
            this.pics.add(blogPic.getPic());
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
