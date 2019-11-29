package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dto.Response;
import com.cmb.o2o.cat.rack.model.Blog;
import com.cmb.o2o.cat.rack.model.BlogPic;
import com.cmb.o2o.cat.rack.model.Store;
import com.cmb.o2o.cat.rack.model.StoreBlogDetail;
import com.cmb.o2o.cat.rack.service.BlogService;
import com.cmb.o2o.cat.rack.service.MallService;
import com.cmb.o2o.cat.rack.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhaopingtao on 2019/11/29
 * Description: No Description
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MallService mallService;

    @Autowired
    private StoreService storeService;



    @RequestMapping("/detail")
    @ResponseBody
    public Response storeBlogs(Integer storeId){
        List<StoreBlogDetail> storeBlogs = new ArrayList<>();
        Map<String,Object> retMap = new HashMap<>();

        Store store = storeService.fetchOne(storeId);

        if (store == null) {
            return Response.fail("store不存在");
        }

        retMap.put("id",store.getId());
        retMap.put("name",store.getName());
        retMap.put("address",store.getAddress());
        retMap.put("type", store.getType());
        retMap.put("pic", store.getPic());


        List<Blog> blogList = blogService.fetchOnline(storeId);
        if (blogList == null ) {
            return Response.fail("blogList不存在");
        }
        if (blogList.size() != 0 ) {
            for (Blog blog : blogList) {
                StoreBlogDetail storeBlog = new StoreBlogDetail();
                List<String> pics = new ArrayList<>();
                storeBlog.setId(blog.getId());
                storeBlog.setContent(blog.getText());
                List<BlogPic> blogPics = blogService.fetchPics(blog.getId());
                if (blogPics == null) {
                    return Response.fail("blogPics不存在");
                }
                for (BlogPic pic : blogPics) {
                    pics.add(pic.getPic());
                }
                storeBlog.setPics(pics);
                Date updateTime = blog.getUpdateTime();
                DateFormat df = new SimpleDateFormat("MM/dd");
                storeBlog.setDatetime(df.format(updateTime));
                storeBlogs.add(storeBlog);
            }
            retMap.put("activityList",storeBlogs);
            return Response.succ(retMap);
        } else {
            retMap.put("activityList","");
            return Response.succ(retMap);
        }

    }

}
