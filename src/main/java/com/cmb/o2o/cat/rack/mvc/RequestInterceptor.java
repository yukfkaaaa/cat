package com.cmb.o2o.cat.rack.mvc;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by qiqing on 2019/11/29.
 */
public class RequestInterceptor implements HandlerInterceptor {

    private static Logger logger= LoggerFactory.getLogger(RequestInterceptor.class);

    private static ThreadLocal<Long> beginTime=new ThreadLocal<>();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String[]> params= request.getParameterMap();

        if(params!=null){
            beginTime.set(System.currentTimeMillis());
            logger.info("uri:{},params:{}", request.getRequestURI(),JSON.toJSONString(params));
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

        long end=System.currentTimeMillis();

        logger.info("uri:{},execute time:{}ms", request.getRequestURI(),end-beginTime.get());

        beginTime.remove();

    }
}
