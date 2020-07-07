package com.platform.entity;

public class ResponseResult {

    private Integer code;
    private boolean flag;
    private String message;
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(Integer code, boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public ResponseResult(Integer code, boolean flag, String message, Object data) {
        this.code = code;
        this.flag = flag;
        this.message = message;
        this.data = data;
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

    public void setData(Object data) {
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

    public Object getData() {
        return data;
    }

}
