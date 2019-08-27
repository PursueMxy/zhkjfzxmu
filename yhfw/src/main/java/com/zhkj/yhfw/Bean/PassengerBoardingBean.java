package com.zhkj.yhfw.Bean;

public class PassengerBoardingBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;

    public PassengerBoardingBean(String event, String token, String type, String send, PassengerBoardingBean.data data) {
        this.event = event;
        this.token = token;
        this.type = type;
        this.send = send;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public PassengerBoardingBean.data getData() {
        return data;
    }

    public void setData(PassengerBoardingBean.data data) {
        this.data = data;
    }

    public static class data{
        public int order_id;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public data(int order_id) {
            this.order_id = order_id;
        }
    }

}
