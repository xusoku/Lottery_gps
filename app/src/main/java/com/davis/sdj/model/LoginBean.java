package com.davis.sdj.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：Rosefinch
 *
 * @author KevinLiu  E-mail: KevinLiu9527@163.com
 * @time 2016/9/7 19:23
 * 描述：登录信息
 */
public class LoginBean implements Serializable {

    /**
     * status : 0
     * msg :
     * data : {"registration_id":"1507bfd3f7cfd782196","class":"Login",
     * "service_group":[{"service_project":"9338","id":39},{"service_project":"9339","id":40},
     * {"service_project":"5315","id":41}],"user_id":4,
     * "im_token":"ll4zEjoMa5TGtt4AagZ0IR1QxCUHswyPXmydJ3lEZf8vRXqPLyR
     * +nI1aVhf3tXUs7Qo4riJWyRiNxuyeu0sefA==","is_customer_service_user":0,
     * "tokenid":"15692176747453994166250353462099","timestamp":1476171241}
     */

    private String status;
    private String msg;
    /**
     * registration_id : 1507bfd3f7cfd782196
     * class : Login
     * service_group : [{"service_project":"9338","id":39},{"service_project":"9339","id":40},
     * {"service_project":"5315","id":41}]
     * user_id : 4
     * im_token : ll4zEjoMa5TGtt4AagZ0IR1QxCUHswyPXmydJ3lEZf8vRXqPLyR
     * +nI1aVhf3tXUs7Qo4riJWyRiNxuyeu0sefA==
     * is_customer_service_user : 0
     * tokenid : 15692176747453994166250353462099
     * timestamp : 1476171241
     */

    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String registration_id;
        @SerializedName("class")
        private String classX;
        private String user_id;
        private String im_token;
        private String is_customer_service_user;
        private String tokenid;
        private String timestamp;
        private String level;
        private String l3;
        private String is_customer_service_admin;
        private String l1;
        private String l4;
        private String l2;
        private List<ServiceGroupBean> service_group;

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getL3() {
            return l3;
        }

        public void setL3(String l3) {
            this.l3 = l3;
        }

        public String getIs_customer_service_admin() {
            return is_customer_service_admin;
        }

        public void setIs_customer_service_admin(String is_customer_service_admin) {
            this.is_customer_service_admin = is_customer_service_admin;
        }

        public String getL1() {
            return l1;
        }

        public void setL1(String l1) {
            this.l1 = l1;
        }

        public String getL4() {
            return l4;
        }

        public void setL4(String l4) {
            this.l4 = l4;
        }

        public String getL2() {
            return l2;
        }

        public void setL2(String l2) {
            this.l2 = l2;
        }

        public String getRegistration_id() {
            return registration_id;
        }

        public void setRegistration_id(String registration_id) {
            this.registration_id = registration_id;
        }

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getIm_token() {
            return im_token;
        }

        public void setIm_token(String im_token) {
            this.im_token = im_token;
        }

        public String getIs_customer_service_user() {
            return is_customer_service_user;
        }

        public void setIs_customer_service_user(String is_customer_service_user) {
            this.is_customer_service_user = is_customer_service_user;
        }

        public String getTokenid() {
            return tokenid;
        }

        public void setTokenid(String tokenid) {
            this.tokenid = tokenid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public List<ServiceGroupBean> getService_group() {
            return service_group;
        }

        public void setService_group(List<ServiceGroupBean> service_group) {
            this.service_group = service_group;
        }

        public static class ServiceGroupBean {
            private String service_project;
            private String id;

            public String getService_project() {
                return service_project;
            }

            public void setService_project(String service_project) {
                this.service_project = service_project;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
