package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.UserTask;
import com.cmb.o2o.cat.rack.model.UserTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTask record);

    int insertSelective(UserTask record);

    List<UserTask> selectByExample(UserTaskExample example);

    UserTask selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserTask record, @Param("example") UserTaskExample example);

    int updateByExample(@Param("record") UserTask record, @Param("example") UserTaskExample example);

    int updateByPrimaryKeySelective(UserTask record);

    int updateByPrimaryKey(UserTask record);

    List<UserTask> selectCompleteTask(@Param("userId") String userId, @Param("taskId") Integer taskId);

}