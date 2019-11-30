package com.cmb.o2o.cat.rack.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmb.o2o.cat.rack.dto.BlogWrapper;
import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.model.Blog;
import com.cmb.o2o.cat.rack.model.BlogPic;
import com.cmb.o2o.cat.rack.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/ops/create")
    @ResponseBody
    public Response createBlog(Integer storeId,String text,String[] pics){
        Blog blog = blogService.createNewBlog(storeId,text);
        if(blog == null){
            return Response.fail("创建失败");
        }
        if(pics!=null && pics.length > 0){
            List<Integer> picAry = new ArrayList<>(pics.length);
            for(String p : pics){
                picAry.add(Integer.valueOf(p));
            }
            blogService.bindPics(blog.getId(),picAry);
        }
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/pass")
    @ResponseBody
    public Response passBlog(Integer blogId){
        blogService.updateBlogStatus(blogId,2);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/refuse")
    @ResponseBody
    public Response refuseBlog(Integer blogId,String reason){
        blogService.updateBlogStatus(blogId,1);
        blogService.updateReason(blogId,reason);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/online")
    @ResponseBody
    public Response onlineBlog(Integer blogId){
        blogService.updateBlogStatus(blogId,2);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/offline")
    @ResponseBody
    public Response offlineBlog(Integer blogId){
        blogService.updateBlogStatus(blogId,3);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/delete")
    @ResponseBody
    public Response deleteBlog(Integer blogId){
        blogService.updateBlogStatus(blogId,4);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("succ",true);
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/list")
    @ResponseBody
    public Response blogListInOps(Integer storeId,Integer isReview){
        List<Blog> blogList = null;
        System.out.println("storeId="+storeId);
        if(isReview == 1){
            blogList = blogService.fetchReview(storeId);
        }else{
            blogList = blogService.fetchAll(storeId);
        }
        System.out.println("blogList="+ JSONObject.toJSONString(blogList));
        List<BlogWrapper> wrapperList = new ArrayList<>(blogList.size());
        for(Blog blog : blogList){
            List<BlogPic> picList = blogService.fetchPics(blog.getId());
            BlogWrapper wrapper = new BlogWrapper(blog,picList);
            wrapperList.add(wrapper);
        }


        Map<String,Object> retMap = new HashMap<>();
        retMap.put("rows",wrapperList);
        retMap.put("size",wrapperList.size());
        System.out.println("wrapperList="+ JSONObject.toJSONString(wrapperList));
        return Response.succ(retMap);
    }

    @RequestMapping("/ops/detail")
    @ResponseBody
    public Response blogDetailInOps(Integer blogId){
        Blog blog = blogService.fetchOne(blogId);
        if(blog == null){
            return Response.fail("blog不存在");
        }
        List<BlogPic> pics = blogService.fetchPics(blog.getId());
        List<String> picAry = new ArrayList<>(pics.size());
        for(BlogPic blogPic : pics){
            picAry.add(blogPic.getPic());
        }
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("blog",blog);
        retMap.put("pics",picAry);
        return Response.succ(retMap);
    }

    @RequestMapping("/app/list")
    @ResponseBody
    public Response blogListInApp(Integer storeId){
        List<Blog> blogList = blogService.fetchAllAvailable(storeId);
        Map<String,Object> retMap = new HashMap<>();
        retMap.put("rows",blogList);
        retMap.put("size",blogList.size());
        return Response.succ(retMap);
    }

    @RequestMapping("/app/report")
    @ResponseBody
    public Response reportBlog(Integer blogId){
        System.out.println("report blogId="+blogId);
        blogService.reportBlog(blogId);
        return Response.succ();
    }

    @RequestMapping("/app/like")
    @ResponseBody
    public Response likeBlog(Integer blogId){
        System.out.println("like blogId="+blogId);
        blogService.likeBlog(blogId);
        return Response.succ();
    }
}
