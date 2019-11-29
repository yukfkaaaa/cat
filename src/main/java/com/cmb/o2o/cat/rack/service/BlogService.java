package com.cmb.o2o.cat.rack.service;

import com.cmb.o2o.cat.rack.common.BlogStatus;
import com.cmb.o2o.cat.rack.common.PicStatus;
import com.cmb.o2o.cat.rack.dao.BlogMapper;
import com.cmb.o2o.cat.rack.dao.BlogPicMapper;
import com.cmb.o2o.cat.rack.model.Blog;
import com.cmb.o2o.cat.rack.model.BlogExample;
import com.cmb.o2o.cat.rack.model.BlogPic;
import com.cmb.o2o.cat.rack.model.BlogPicExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BlogService {
    @Autowired
    private BlogPicMapper blogPicMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Transactional(rollbackFor = Exception.class)
    public List<Integer> savePics(List<String> pics){
        List<Integer> picIds = new ArrayList<>(pics.size());
        for(String pic : pics){
            BlogPic bp = new BlogPic();
            bp.setBlogId(0);
            bp.setCreateTime(new Date());
            bp.setPic(pic);
            bp.setStatus(PicStatus.UPLOAD);
            blogPicMapper.insert(bp);
            picIds.add(bp.getId());
        }
        return picIds;
    }

    public Blog createNewBlog(Integer storeId, String text) {
        Blog blog = new Blog();
        blog.setStoreId(storeId);
        blog.setText(text);
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setStatus(BlogStatus.PREPARE);
        blog.setSpStatus(BlogStatus.SP_NORMAL);
        blog.setMsg("");
        blog.setReportNum(0);
        blogMapper.insert(blog);
        return blog;
    }

    public void bindPics(Integer blogId, List<Integer> pics) {
        BlogPicExample example = new BlogPicExample();
        BlogPicExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(pics);
        BlogPic param = new BlogPic();
        param.setBlogId(blogId);
        param.setStatus(PicStatus.BIND);
        blogPicMapper.updateByExampleSelective(param,example);
    }

    public void updateBlogStatus(Integer blogId, int status) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(blogId);
        Blog blog = new Blog();
        blog.setStatus(status);
        blogMapper.updateByExampleSelective(blog,example);
    }

    public List<Blog> fetchAll(Integer storeId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId).andStatusNotEqualTo(BlogStatus.DELETE);
        example.setOrderByClause("update_time desc");
        return blogMapper.selectByExample(example);
    }

//    拉取在线的 Blog
    public List<Blog> fetchOnline(Integer storeId) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId).andStatusEqualTo(BlogStatus.ONLINE);
        return blogMapper.selectByExample(example);
    }

    public Blog fetchOne(Integer blogId) {
        return blogMapper.selectByPrimaryKey(blogId);
    }

    public List<BlogPic> fetchPics(Integer id) {
        BlogPicExample example = new BlogPicExample();
        BlogPicExample.Criteria criteria = example.createCriteria();
        criteria.andBlogIdEqualTo(id).andStatusEqualTo(PicStatus.BIND);
        return blogPicMapper.selectByExample(example);
    }

    public List<Blog> fetchAllAvailable(Integer storeId) {
        return null;
    }

    public void reportBlog(Integer blogId){
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(blogId);
        Blog blog = new Blog();
        blog.setReportNum((blog.getReportNum()==null? 0 : blog.getReportNum()) + 1);
        System.out.println("reportNum="+ blog.getReportNum());
        blogMapper.updateByExampleSelective(blog,example);
    }

    public void likeBlog(Integer blogId){
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(blogId);
        Blog blog = new Blog();
        blog.setLikeNum((blog.getLikeNum()==null? 0 : blog.getLikeNum()) + 1);
        System.out.println("likeNum="+ blog.getLikeNum());
        blogMapper.updateByExampleSelective(blog,example);
    }

    public List<Blog> fetchReview(Integer storeId) {
        List<Integer> status = Arrays.asList(BlogStatus.PREPARE,BlogStatus.ONLINE,BlogStatus.ONLINE);
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andStoreIdEqualTo(storeId).andStatusIn(status);
        example.setOrderByClause("update_time desc");
        return blogMapper.selectByExample(example);
    }

    public void updateReason(Integer blogId, String reason) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(blogId);
        Blog blog = new Blog();
        blog.setMsg(reason);
        blogMapper.updateByExampleSelective(blog,example);
    }
}
