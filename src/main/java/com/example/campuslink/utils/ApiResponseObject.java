package com.example.campuslink.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@ApiModel(description = "Standard API response object")
public class ApiResponseObject {
    @ApiModelProperty(value = "Response code", example = "200")
    private int code;
    @ApiModelProperty(value = "Response message", example = "action successful")
    private String msg;
    @ApiModelProperty(value = "Response data", example = "{respondData: data}")
    private Map<String, Object> data;

    public ApiResponseObject() {
        this.data = new HashMap<>();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }


    public void insertData(String key, Object value) {
        this.data.put(key, value);
    }
    public Object getDataByName (String key) {
        return this.data.get(key);
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
