package com.zhkj.yhfw.Bean;

public class TeamBean {

    /**
     * code : 200
     * msg : ok
     * time : 1566984718
     * data : {"money":"0.00","fname":"无推荐人"}
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
         * money : 0.00
         * fname : 无推荐人
         */

        private String money;
        private String fname;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }
    }
}
