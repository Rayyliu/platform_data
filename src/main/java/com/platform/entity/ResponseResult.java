package com.platform.entity;


import org.springframework.stereotype.Component;

@Component
public class ResponseResult<T> {

    private Integer code;
    private boolean flag;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public ResponseResult(Integer code, boolean flag, String message, T data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public   ResponseResult<T> success(Integer code,boolean flag,String message,T data){
        return new ResponseResult(code,true,message,data);
    }

    public  ResponseResult<T> fail(Integer code,String message,T data){
        return new ResponseResult(code,message,data);
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

}
