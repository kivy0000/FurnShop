package com.kfhstu.furn.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author KFH
 * @version 1.0
 * 这是要响应给前端的json数据类
 */
public class Msg implements Serializable {
    //响应码 200成功，400失败
    private Integer code;
    //描述信息
    private String msg;
    //要响应的数据
    private Map<String, Object> extend = new HashMap<>(16);

    //填充数据的方法
    public Msg add(String key,Object val){
        extend.put(key, val);
        return this;
    }


    //获取成功数据对象（未填充响应数据的）
    public static Msg success() {
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("success");
        return msg;
    }

    //获取失败数据对象（未填充响应数据）
    public static Msg fail() {
        Msg msg = new Msg();
        msg.setCode(400);
        msg.setMsg("fail");
        return msg;
    }

    public Msg() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
