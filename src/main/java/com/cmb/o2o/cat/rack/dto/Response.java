package com.cmb.o2o.cat.rack.dto;

public class Response<T> {
    private String code;
    private String msg;
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final String FAIL_CODE = "9999";
    public static final String SUCC_CODE = "1000";


    public static Response fail(String msg){
        Response response = new Response();
        response.setCode(FAIL_CODE);
        response.setMsg(msg);
        return response;
    }

    public static <T>  Response succ(T data){
        Response response = new Response();
        response.setCode(SUCC_CODE);
        response.setData(data);
        return response;
    }

    public static Response succ(){
        Response response = new Response();
        response.setCode(SUCC_CODE);
        return response;
    }
}
