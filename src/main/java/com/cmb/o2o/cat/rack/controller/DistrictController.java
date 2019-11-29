package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dao.DistrictMapper;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.form.DistrictForm;
import com.cmb.o2o.cat.rack.model.District;
import com.cmb.o2o.cat.rack.vo.DistrictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dragic
 * @create 2019/11/30 00:21
 */
@RestController
public class DistrictController {
    @Autowired
    private DistrictMapper districtMapper;

    @RequestMapping("/mall/list")
    public Response mallList(DistrictForm form) {
        return Response.succ(convertFromModelList(districtMapper.selectAll()));
    }

    @RequestMapping("/mall/detail")
    public Response mallDetail(DistrictForm form) {
        Integer id = form.getId();
        return Response.succ(convertFromModel(districtMapper.selectByPrimaryKey(id)));
    }

    private DistrictVO convertFromModel(District district) {
        DistrictVO districtVO = new DistrictVO();
        districtVO.setId(district.getId());
        districtVO.setName(district.getDistrictName());
        districtVO.setAddress(district.getAddress());
        districtVO.setBusinessHours(district.getOpenHour());
        districtVO.setDistance(district.getDistance());
        return districtVO;
    }

    private List<DistrictVO> convertFromModelList(List<District> districtList) {
        if(!CollectionUtils.isEmpty(districtList)) {
            List<DistrictVO> districtVOList = new ArrayList<>(districtList.size());
            for (District item : districtList) {
                districtVOList.add(convertFromModel(item));
            }
            return districtVOList;
        }
        return null;
    }
}