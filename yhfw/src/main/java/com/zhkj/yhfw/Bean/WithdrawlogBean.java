package com.zhkj.yhfw.Bean;

import java.util.List;

public class WithdrawlogBean {

    /**
     * code : 200
     * msg : ok
     * time : 1567081383
     * data : {"list":[{"id":1,"status":"3","money":"1.00","createtime":1567067099,"verifytime":1567067099,"mmt":1567067099,"driver_id":3,"withdrawal_id":4},{"id":15,"status":"1","money":"1.00","createtime":1567067099,"verifytime":1567067099,"mmt":1567067099,"driver_id":3,"withdrawal_id":4},{"id":16,"status":"2","money":"1.00","createtime":1567067099,"verifytime":1567067099,"mmt":1567067099,"driver_id":3,"withdrawal_id":4},{"id":17,"status":"4","money":"1.00","createtime":1567067099,"verifytime":1567067099,"mmt":1567067099,"driver_id":3,"withdrawal_id":4}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1
             * status : 3
             * money : 1.00
             * createtime : 1567067099
             * verifytime : 1567067099
             * mmt : 1567067099
             * driver_id : 3
             * withdrawal_id : 4
             */

            private int id;
            private String status;
            private String money;
            private int createtime;
            private int verifytime;
            private int mmt;
            private int driver_id;
            private int withdrawal_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getVerifytime() {
                return verifytime;
            }

            public void setVerifytime(int verifytime) {
                this.verifytime = verifytime;
            }

            public int getMmt() {
                return mmt;
            }

            public void setMmt(int mmt) {
                this.mmt = mmt;
            }

            public int getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(int driver_id) {
                this.driver_id = driver_id;
            }

            public int getWithdrawal_id() {
                return withdrawal_id;
            }

            public void setWithdrawal_id(int withdrawal_id) {
                this.withdrawal_id = withdrawal_id;
            }
        }
    }
}
