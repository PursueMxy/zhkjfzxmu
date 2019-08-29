package com.zhkj.yhfw.Bean;

public class ChargingDocBean {

    /**
     * code : 200
     * msg : 请求成功
     * time : 1567071583
     * data : {"charging_doc":"ssssss"}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * charging_doc : ssssss
         */

        private String charging_doc;

        public String getCharging_doc() {
            return charging_doc;
        }

        public void setCharging_doc(String charging_doc) {
            this.charging_doc = charging_doc;
        }
    }
}
