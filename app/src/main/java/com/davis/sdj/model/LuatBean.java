package com.davis.sdj.model;

import java.io.Serializable;

/**
 * 项目名称：Peafowl
 * 创建人：  KevinLiu
 * 创建时间：2017/4/5 17:33
 * 邮箱：KevinLiu9527@163.com
 * QQ:779425729
 * 类描述：Luat内容
 */

public class LuatBean implements Serializable {

    private static final long serialVersionUID = -6950342057933380991L;
    private String lng;
    private String lat;

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
