package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSON;
import com.cmb.o2o.cat.rack.dao.BlogMapper;
import com.cmb.o2o.cat.rack.model.BlogExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private BlogMapper blogMapper;

    @RequestMapping("/")
    @ResponseBody
    public String index(String[] t){
        logger.info(JSON.toJSONString(t));
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(1).andSpStatusIn(Arrays.asList(1,2));
        return JSON.toJSONString(blogMapper.selectByExample(example));
    }
}
