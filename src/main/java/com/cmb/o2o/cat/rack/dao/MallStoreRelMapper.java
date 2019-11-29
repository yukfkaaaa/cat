package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.*;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MallStoreRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallStoreRel record);

    int insertSelective(MallStoreRel record);

    List<MallStoreRel> selectByExample(MallStoreRelExample example);

    MallStoreRel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallStoreRel record, @Param("example") MallStoreRelExample example);

    int updateByExample(@Param("record") MallStoreRel record, @Param("example") MallStoreRelExample example);

    int updateByPrimaryKeySelective(MallStoreRel record);

    int updateByPrimaryKey(MallStoreRel record);

    List<Integer> queryStoreIdByMallId(@Param("mallId") Integer mallId);

    StoreBlog queryBlogByStoreId(@Param("storeId") Integer storeId);

    List<String> queryPicsByBlogId(@Param("blogId") Integer blogId);

    Store queryStoreById(@Param("storeId") Integer storeId);
}