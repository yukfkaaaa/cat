package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {



    @RequestMapping("/")
    @ResponseBody
    public String index(){

        return JSON.toJSONString(null);
    }
}
