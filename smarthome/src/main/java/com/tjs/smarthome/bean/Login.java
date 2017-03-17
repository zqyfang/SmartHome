package com.tjs.smarthome.bean;

/**
 * 登录的对应javabean
 * Created by tjsmc on 17/3/1.
 */

public class Login {

    /**
     * StatusCode : 200
     * Info : 请求(或处理)成功
     * Data : {"ApplicationId":"TJS_App_3.0","Id":"b884c081eff91bf","Key":"9a5ac6f9653c91bf","ExpireTime":"2017-03-01T14:02:40"}
     */

    private int StatusCode;
    private String Info;
    private DataBean Data;

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int StatusCode) {
        this.StatusCode = StatusCode;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String Info) {
        this.Info = Info;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public static class DataBean {
        /**
         * ApplicationId : TJS_App_3.0
         * Id : b884c081eff91bf
         * Key : 9a5ac6f9653c91bf
         * ExpireTime : 2017-03-01T14:02:40
         */

        private String ApplicationId;
        private String Id;
        private String Key;
        private String ExpireTime;

        public String getApplicationId() {
            return ApplicationId;
        }

        public void setApplicationId(String ApplicationId) {
            this.ApplicationId = ApplicationId;
        }

        public String getId() {
            return Id;
        }

        public void setId(String Id) {
            this.Id = Id;
        }

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public String getExpireTime() {
            return ExpireTime;
        }

        public void setExpireTime(String ExpireTime) {
            this.ExpireTime = ExpireTime;
        }
    }
}
