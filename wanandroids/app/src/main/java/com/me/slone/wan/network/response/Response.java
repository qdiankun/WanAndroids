package com.me.slone.wan.network.response;

/**
 * Author：diankun
 * Time：20-7-13 下午3:35
 * Description: 服务端返回数据
 */
public class Response<T> {

    private int errorCode;
    private String errorMsg;
    private T data;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }
}
