package com.davis.sdj.model;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：Peafowl
 * 创建人：  KevinLiu
 * 创建时间：2017/4/5 17:31
 * 邮箱：KevinLiu9527@163.com
 * QQ:779425729
 * 类描述：luat数据
 */

public class LuatDataBean implements Serializable {

    private static final long serialVersionUID = 7677649375250979106L;
    private String msg;
    private String status;
    private List<LuatBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LuatBean> getData() {
        return data;
    }

    public void setData(List<LuatBean> data) {
        this.data = data;
    }

}
