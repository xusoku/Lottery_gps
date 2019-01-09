package com.davis.sdj.model;

import java.io.Serializable;

/**
 * 项目名称：Rosefinch
 * 创建人：  KevinLiu  E-mail: KevinLiu9527@163.com
 * 创建时间：2016/8/31 16:21
 * 类描述：用户信息
 */
public class UserInFor implements Serializable {
    private static final long serialVersionUID = 3002634012249332071L;


    private String status;
    private DataBean data;
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        private String qq;
        private String wechat;
        private String name;
        private int gender;
        private String mobile;
        private String location;
        private String nickname;
        private String real_name;
        private String l1;
        private String l2;
        private String l3;
        private String l4;
        private String id_number;
        private String real_phone;
        private String address;
        private String avatar;
        //判断是第几个Luat使用者
        private String binding_rank;

        public String getBinding_rank() {
            return binding_rank;
        }

        public void setBinding_rank(String binding_rank) {
            this.binding_rank = binding_rank;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getL1() {
            return l1;
        }

        public void setL1(String l1) {
            this.l1 = l1;
        }

        public String getL2() {
            return l2;
        }

        public void setL2(String l2) {
            this.l2 = l2;
        }

        public String getL3() {
            return l3;
        }

        public void setL3(String l3) {
            this.l3 = l3;
        }

        public String getL4() {
            return l4;
        }

        public void setL4(String l4) {
            this.l4 = l4;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getReal_phone() {
            return real_phone;
        }

        public void setReal_phone(String real_phone) {
            this.real_phone = real_phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
