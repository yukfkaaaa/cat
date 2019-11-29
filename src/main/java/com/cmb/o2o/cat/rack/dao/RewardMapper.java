package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.Reward;
import com.cmb.o2o.cat.rack.model.RewardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RewardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reward record);

    int insertSelective(Reward record);

    List<Reward> selectByExample(RewardExample example);

    Reward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reward record, @Param("example") RewardExample example);

    int updateByExample(@Param("record") Reward record, @Param("example") RewardExample example);

    int updateByPrimaryKeySelective(Reward record);

    int updateByPrimaryKey(Reward record);
}