package com.davis.sdj.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class GetDeviceListBean implements Serializable {

    private static final long serialVersionUID = 5475142462241404281L;
    private String status;
    private String msg;


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
        @SerializedName("class")
        private String classX;
        private String timestamp;
        /**
         * online : 1
         * sim : 862991419752644
         * name : 定位器
         * id : 7
         * imei : 862991419752644
         */

        private List<DevicesBean> devices;

        public String getClassX() {
            return classX;
        }

        public void setClassX(String classX) {
            this.classX = classX;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }


        /**
         * 设备列表
         * is_owner，1代表是管理员。
         */
        public static class DevicesBean {
            private String online;
            private String sim;
            private String name;
            private String id;
            private String imei;
            private String project_id;
            private String dir;
            private String iccid;
            private String version;
            private String is_owner;
            private String phone;
            /*是否默认终端*/
            private boolean isDefault;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getIs_owner() {
                return is_owner;
            }

            public void setIs_owner(String is_owner) {
                this.is_owner = is_owner;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getIccid() {
                return iccid;
            }

            public void setIccid(String iccid) {
                this.iccid = iccid;
            }

            public String getProject_id() {
                return project_id;
            }

            public void setProject_id(String project_id) {
                this.project_id = project_id;
            }

            public boolean isDefault() {
                return isDefault;
            }

            public void setDefault(boolean aDefault) {
                isDefault = aDefault;
            }

            public String getOnline() {
                return online;
            }

            public void setOnline(String online) {
                this.online = online;
            }

            public String getSim() {
                return sim;
            }

            public void setSim(String sim) {
                this.sim = sim;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }
        }
    }


}
