package com.zhkj.yhfw.Bean;

import java.util.List;

public class PurseBean {

    /**
     * code : 200
     * msg : ok
     * time : 1565578200
     * data : {"money":"9627.53","money_log":[{"createtime":1565242814,"cut_money":"136.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-136.09"},{"createtime":1565170220,"money":"4.09","event":"管理员操作"},{"createtime":1565170220,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565163244,"money":"4.09","event":"管理员操作"},{"createtime":1565163244,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565162273,"money":"4.09","event":"管理员操作"},{"createtime":1565162273,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565159963,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565159089,"cut_money":"23.56","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-23.56"},{"createtime":1565087047,"cut_money":"12.45","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-12.45"},{"createtime":1564998995,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564997933,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564997125,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564996566,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564810558,"cut_money":"25.98","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-25.98"},{"createtime":1564807122,"money":"5.19","event":"管理员操作"},{"createtime":1564807122,"cut_money":"5.19","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-5.19"},{"createtime":1564773949,"money":"5.19","event":"管理员操作"},{"createtime":1564773949,"cut_money":"5.19","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-5.19"}]}
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
         * money : 9627.53
         * money_log : [{"createtime":1565242814,"cut_money":"136.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-136.09"},{"createtime":1565170220,"money":"4.09","event":"管理员操作"},{"createtime":1565170220,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565163244,"money":"4.09","event":"管理员操作"},{"createtime":1565163244,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565162273,"money":"4.09","event":"管理员操作"},{"createtime":1565162273,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565159963,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1565159089,"cut_money":"23.56","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-23.56"},{"createtime":1565087047,"cut_money":"12.45","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-12.45"},{"createtime":1564998995,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564997933,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564997125,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564996566,"cut_money":"4.09","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-4.09"},{"createtime":1564810558,"cut_money":"25.98","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-25.98"},{"createtime":1564807122,"money":"5.19","event":"管理员操作"},{"createtime":1564807122,"cut_money":"5.19","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-5.19"},{"createtime":1564773949,"money":"5.19","event":"管理员操作"},{"createtime":1564773949,"cut_money":"5.19","paytype":"线下支付","discount":"0.00","event":"服务费（线下)","money":"-5.19"}]
         */

        private String money;
        private List<MoneyLogBean> money_log;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public List<MoneyLogBean> getMoney_log() {
            return money_log;
        }

        public void setMoney_log(List<MoneyLogBean> money_log) {
            this.money_log = money_log;
        }

        public static class MoneyLogBean {
            /**
             * createtime : 1565242814
             * cut_money : 136.09
             * paytype : 线下支付
             * discount : 0.00
             * event : 服务费（线下)
             * money : -136.09
             */

            private int createtime;
            private String cut_money;
            private String paytype;
            private String discount;
            private String event;
            private String money;

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getCut_money() {
                return cut_money;
            }

            public void setCut_money(String cut_money) {
                this.cut_money = cut_money;
            }

            public String getPaytype() {
                return paytype;
            }

            public void setPaytype(String paytype) {
                this.paytype = paytype;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getEvent() {
                return event;
            }

            public void setEvent(String event) {
                this.event = event;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }
        }
    }
}
