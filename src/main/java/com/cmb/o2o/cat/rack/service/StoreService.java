package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.dao.StoreMapper;
import com.cmb.o2o.cat.rack.model.Store;
import com.cmb.o2o.cat.rack.model.StoreExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaopingtao on 2019/11/29
 * Description: No Description
 */
@Service
public class StoreService {

    @Autowired
    private StoreMapper storeMapper;

    //    获取门店在线的 blog
    public Store fetchOne(Integer storeId) {
        return storeMapper.selectByPrimaryKey(storeId);
    }

}