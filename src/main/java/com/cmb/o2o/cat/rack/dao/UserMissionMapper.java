package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.UserMission;
import com.cmb.o2o.cat.rack.model.UserMissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserMission record);

    int insertSelective(UserMission record);

    List<UserMission> selectByExample(UserMissionExample example);

    UserMission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserMission record, @Param("example") UserMissionExample example);

    int updateByExample(@Param("record") UserMission record, @Param("example") UserMissionExample example);

    int updateByPrimaryKeySelective(UserMission record);

    int updateByPrimaryKey(UserMission record);
}