package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.Blog;
import com.cmb.o2o.cat.rack.model.BlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    List<Blog> selectByExample(BlogExample example);

    Blog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    void reportBlog(@Param("blogId") Integer blogId);
}