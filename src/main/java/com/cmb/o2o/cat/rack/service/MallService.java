package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.dao.MallStoreRelMapper;
import com.cmb.o2o.cat.rack.model.StoreBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallService {
    @Autowired
    private MallStoreRelMapper mallStoreRelMapper;

    public List<StoreBlog> queryMallDynamics(Integer mallId){
        return mallStoreRelMapper.queryByMallId(mallId);

    }

}
