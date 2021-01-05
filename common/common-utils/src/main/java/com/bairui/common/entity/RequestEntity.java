package com.bairui.common.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 返回Map对象
*/
public class RequestEntity extends HashMap<String,Object> implements Serializable {
    public String getString(String  objKey){
        String  strVal = "";
        try{
            strVal = this .get(objKey).toString();
        }catch (Exception e){

        }
        return  strVal;
    }
    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
