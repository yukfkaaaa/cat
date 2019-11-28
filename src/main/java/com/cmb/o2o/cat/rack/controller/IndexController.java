package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSON;
import com.cmb.o2o.cat.rack.dao.BudgetDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.util.BuddhistCalendar;

@RestController
public class IndexController {
    @Autowired
    private BudgetDetailMapper mapper;


    @RequestMapping("/")
    @ResponseBody
    public String index(){

        return JSON.toJSONString(mapper.selectByPrimaryKey(1L));
    }
}
