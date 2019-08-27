package com.zhkj.yhfw.Bean;

public class DriverArriveBean {
    public String token;
    public  String type;
    public String send;
    public String event;
    public data data;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public DriverArriveBean.data getData() {
        return data;
    }

    public void setData(DriverArriveBean.data data) {
        this.data = data;
    }

    public DriverArriveBean(String token, String type, String send, String event, DriverArriveBean.data data) {
        this.token = token;
        this.type = type;
        this.send = send;
        this.event = event;
        this.data = data;
    }

    public static class  data{
        public String city;
        public int order_id;
        public String position;

        public String positionE;

        public String positionN;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getPositionE() {
            return positionE;
        }

        public void setPositionE(String positionE) {
            this.positionE = positionE;
        }

        public String getPositionN() {
            return positionN;
        }

        public void setPositionN(String positionN) {
            positionN = positionN;
        }

        public data(String city, int order_id, String position, String positionE, String positionN) {
            this.city = city;
            this.order_id = order_id;
            this.position = position;
            this.positionE = positionE;
            this.positionN = positionN;
        }
    }
}
