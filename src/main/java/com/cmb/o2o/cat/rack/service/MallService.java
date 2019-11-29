package com.cmb.o2o.cat.rack.service;

import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.dao.MallStoreRelMapper;
import com.cmb.o2o.cat.rack.model.Store;
import com.cmb.o2o.cat.rack.model.StoreBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MallService {
    @Autowired
    private MallStoreRelMapper mallStoreRelMapper;

    public List<StoreBlog> queryMallDynamics(Integer mallId){
        List<Integer> storeIds = mallStoreRelMapper.queryStoreIdByMallId(mallId);
        System.out.println("storeIds="+ JSONObject.toJSONString(storeIds));
        List<StoreBlog> retList = new ArrayList();
        storeIds.stream().forEach(storeId -> {
            StoreBlog storeBlog = mallStoreRelMapper.queryBlogByStoreId(storeId);
            List<String> pics = mallStoreRelMapper.queryPicsByBlogId(storeBlog.getBlogId());
            Store store = mallStoreRelMapper.queryStoreById(storeId);
            storeBlog.setStoreName(store.getName());
            storeBlog.setMallId(mallId);
            storeBlog.setPics(pics);
            retList.add(storeBlog);
        });
        return retList;
    }

}
