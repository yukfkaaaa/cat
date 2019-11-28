package com.cmb.o2o.cat.rack.dao;

import com.cmb.o2o.cat.rack.model.BlogPic;
import com.cmb.o2o.cat.rack.model.BlogPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlogPicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPic record);

    int insertSelective(BlogPic record);

    List<BlogPic> selectByExample(BlogPicExample example);

    BlogPic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BlogPic record, @Param("example") BlogPicExample example);

    int updateByExample(@Param("record") BlogPic record, @Param("example") BlogPicExample example);

    int updateByPrimaryKeySelective(BlogPic record);

    int updateByPrimaryKey(BlogPic record);
}