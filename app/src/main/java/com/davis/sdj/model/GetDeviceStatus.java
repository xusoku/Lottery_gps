package com.davis.sdj.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/8/11.
 */
public class GetDeviceStatus implements Serializable {


    /**
     * status : 0
     * msg :
     * data : {"class":"GetDeviceStatus","timestamp":1473496185,"device_infos":[{"vtg":"4.13","model":0,"id":"7","method":0,"online":1,"speed":0,"imei":"862991419752644","signal":23,"lat":"31.2416750","device_name":"定位器","iccid":"898602B30116C0377264","loc_time":"2016-09-10 16:28:53","power":0,"userno":"862991419752644","sim":"862991419752644","lng":"121.4733666","gps":0}]}
     */

    private String status;
    private String msg;
    /**
     * class : GetDeviceStatus
     * timestamp : 1473496185
     * device_infos : [{"vtg":"4.13","model":0,"id":"7","method":0,"online":1,"speed":0,"imei":"862991419752644","signal":23,"lat":"31.2416750","device_name":"定位器","iccid":"898602B30116C0377264","loc_time":"2016-09-10 16:28:53","power":0,"userno":"862991419752644","sim":"862991419752644","lng":"121.4733666","gps":0}]
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
        @SerializedName("class")
        private String classX;
        private String timestamp;


        private List<DeviceInfosBean> device_infos;

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

        public List<DeviceInfosBean> getDevice_infos() {
            return device_infos;
        }

        public void setDevice_infos(List<DeviceInfosBean> device_infos) {
            this.device_infos = device_infos;
        }

        public static class DeviceInfosBean {
            private String vtg;
            private String model;
            private String id;
            private String method;
            private String online;
            private String speed;
            private String imei;
            private String signal;
            private String lat;
            private String device_name;
            private String iccid;
            private String loc_time;
            private String power;
            private String userno;
            private String sim;
            private String lng;
            private String gps;
            private String project_id;
            private String dir;
            private String version;
            private String charging;
            private String stayed_time;
            private String is_owner;
            private String phone;
            private String guarding;
            private String imsi;

            public String getImsi() {
                return imsi;
            }

            public void setImsi(String imsi) {
                this.imsi = imsi;
            }

            public String getGuarding() {
                return guarding;
            }

            public void setGuarding(String guarding) {
                this.guarding = guarding;
            }

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

            public String getStayed_time() {
                return stayed_time;
            }

            public void setStayed_time(String stayed_time) {
                this.stayed_time = stayed_time;
            }

            public String getCharging() {
                return charging;
            }

            public void setCharging(String charging) {
                this.charging = charging;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public String getProject_id() {
                return project_id;
            }

            public void setProject_id(String project_id) {
                this.project_id = project_id;
            }

            public String getVtg() {
                return vtg;
            }

            public void setVtg(String vtg) {
                this.vtg = vtg;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getMethod() {
                return method;
            }

            public void setMethod(String method) {
                this.method = method;
            }

            public String getOnline() {
                return online;
            }

            public void setOnline(String online) {
                this.online = online;
            }

            public String getSpeed() {
                return speed;
            }

            public void setSpeed(String speed) {
                this.speed = speed;
            }

            public String getImei() {
                return imei;
            }

            public void setImei(String imei) {
                this.imei = imei;
            }

            public String getSignal() {
                return signal;
            }

            public void setSignal(String signal) {
                this.signal = signal;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getDevice_name() {
                return device_name;
            }

            public void setDevice_name(String device_name) {
                this.device_name = device_name;
            }

            public String getIccid() {
                return iccid;
            }

            public void setIccid(String iccid) {
                this.iccid = iccid;
            }

            public String getLoc_time() {
                return loc_time;
            }

            public void setLoc_time(String loc_time) {
                this.loc_time = loc_time;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getUserno() {
                return userno;
            }

            public void setUserno(String userno) {
                this.userno = userno;
            }

            public String getSim() {
                return sim;
            }

            public void setSim(String sim) {
                this.sim = sim;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getGps() {
                return gps;
            }

            public void setGps(String gps) {
                this.gps = gps;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }
        }
    }
}
