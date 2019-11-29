package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.UserReward;
import com.cmb.o2o.cat.rack.model.UserRewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRewardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserReward record);

    int insertSelective(UserReward record);

    List<UserReward> selectByExample(UserRewardExample example);

    UserReward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserReward record, @Param("example") UserRewardExample example);

    int updateByExample(@Param("record") UserReward record, @Param("example") UserRewardExample example);

    int updateByPrimaryKeySelective(UserReward record);

    int updateByPrimaryKey(UserReward record);
}