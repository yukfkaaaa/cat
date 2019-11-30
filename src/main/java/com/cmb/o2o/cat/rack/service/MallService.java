package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.dao.DistrictMapper;
import com.cmb.o2o.cat.rack.dao.MallStoreRelMapper;
import com.cmb.o2o.cat.rack.form.MissionConsoleForm;
import com.cmb.o2o.cat.rack.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MallService {
    @Autowired
    private MallStoreRelMapper mallStoreRelMapper;

    @Autowired
    DistrictMapper districtMapper;

    public List<StoreBlog> queryMallDynamics(Integer mallId){
        List<Integer> storeIds = mallStoreRelMapper.queryStoreIdByMallId(mallId);
        List<StoreBlog> retList = new ArrayList();
        storeIds.stream().forEach(storeId -> {
            StoreBlog storeBlog = mallStoreRelMapper.queryBlogByStoreId(storeId);
            if(storeBlog != null){
                List<String> pics = mallStoreRelMapper.queryPicsByBlogId(storeBlog.getBlogId());
                Store store = mallStoreRelMapper.queryStoreById(storeId);
                storeBlog.setStoreName(store.getName());
                storeBlog.setMallId(mallId);
                storeBlog.setPics(pics);
                storeBlog.setPic(store.getPic());
                retList.add(storeBlog);
            }
        });
        return retList;
    }

    /*商圈列表*/
    public List<District> queryMalls(MissionConsoleForm form){
        return districtMapper.selectByParams(form.getCityName(),form.getMallName());
    }


}
