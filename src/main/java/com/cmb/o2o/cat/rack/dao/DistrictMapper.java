package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.District;
import com.cmb.o2o.cat.rack.model.DistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DistrictMapper {
    long countByExample(DistrictExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByExample(@Param("record") District record, @Param("example") DistrictExample example);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    List<District> selectAll();
}