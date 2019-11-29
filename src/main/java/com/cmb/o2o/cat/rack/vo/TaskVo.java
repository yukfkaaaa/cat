package com.cmb.o2o.cat.rack.vo;

/**
 * @author dragic
 * @create 2019/11/29 16:33
 */
public class TaskVo {
    /**
     * 子任务id
     */
    private Integer id;

    /**
     * 子任务名称
     */
    private String name;

    /**
     * 子任务完成状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}