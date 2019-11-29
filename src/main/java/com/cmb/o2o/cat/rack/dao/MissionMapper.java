package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.Mission;
import com.cmb.o2o.cat.rack.model.MissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mission record);

    int insertSelective(Mission record);

    List<Mission> selectByExample(MissionExample example);

    Mission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByExample(@Param("record") Mission record, @Param("example") MissionExample example);

    int updateByPrimaryKeySelective(Mission record);

    int updateByPrimaryKey(Mission record);

    /**
     * 通过商圈id取该商圈下的所有mission
     * @param districtId 商圈id
     * @return mission list
     */
    List<Mission> selectByDistrictId(@Param("districtId") Integer districtId);

}