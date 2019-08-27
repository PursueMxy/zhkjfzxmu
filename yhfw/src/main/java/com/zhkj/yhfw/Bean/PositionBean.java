package com.zhkj.yhfw.Bean;

public class PositionBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;

    public PositionBean(String event, String token, String type, String send, PositionBean.data data) {
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

    public PositionBean.data getData() {
        return data;
    }

    public void setData(PositionBean.data data) {
        this.data = data;
    }

    public static class  data{
        public String city;
        public String position;
        public String positionE;

        public String positionN;

        public data(String city, String position, String positionE, String positionN) {
            this.city = city;
            this.position = position;
            this.positionE = positionE;
            this.positionN = positionN;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
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
            this.positionN = positionN;
        }
    }
}
