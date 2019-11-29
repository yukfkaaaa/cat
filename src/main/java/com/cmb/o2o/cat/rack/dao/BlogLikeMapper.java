package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.BlogLike;
import com.cmb.o2o.cat.rack.model.BlogLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogLikeMapper {
    long countByExample(BlogLikeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BlogLike record);

    int insertSelective(BlogLike record);

    List<BlogLike> selectByExample(BlogLikeExample example);

    BlogLike selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogLike record, @Param("example") BlogLikeExample example);

    int updateByExample(@Param("record") BlogLike record, @Param("example") BlogLikeExample example);

    int updateByPrimaryKeySelective(BlogLike record);

    int updateByPrimaryKey(BlogLike record);
}