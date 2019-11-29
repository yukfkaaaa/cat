package com.cmb.o2o.cat.rack.controller;

import com.cmb.o2o.cat.rack.dto.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qiqing on 2019/11/29.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response processExe(Exception e){
        e.printStackTrace();

        return Response.fail("系统异常");
    }
}
