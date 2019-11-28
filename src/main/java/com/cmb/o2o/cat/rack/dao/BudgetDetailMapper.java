package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.BudgetDetail;
import com.cmb.o2o.cat.rack.model.BudgetDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BudgetDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BudgetDetail record);

    int insertSelective(BudgetDetail record);

    List<BudgetDetail> selectByExample(BudgetDetailExample example);

    BudgetDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BudgetDetail record, @Param("example") BudgetDetailExample example);

    int updateByExample(@Param("record") BudgetDetail record, @Param("example") BudgetDetailExample example);

    int updateByPrimaryKeySelective(BudgetDetail record);

    int updateByPrimaryKey(BudgetDetail record);
}