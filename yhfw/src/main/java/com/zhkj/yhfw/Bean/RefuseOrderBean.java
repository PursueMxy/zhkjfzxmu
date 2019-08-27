package com.zhkj.yhfw.Bean;

public class RefuseOrderBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;

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

    public RefuseOrderBean.data getData() {
        return data;
    }

    public void setData(RefuseOrderBean.data data) {
        this.data = data;
    }

    public RefuseOrderBean(String event, String token, String type, String send, RefuseOrderBean.data data) {
        this.event = event;
        this.token = token;
        this.type = type;
        this.send = send;
        this.data = data;
    }

    public static class  data{
        public String city;
        public  int order_id;
        public  String position;
        public String positionE;
        public String posutionN;
        public String reason;

        public data(String city, int order_id, String position, String positionE, String posutionN, String reason) {
            this.city = city;
            this.order_id = order_id;
            this.position = position;
            this.positionE = positionE;
            this.posutionN = posutionN;
            this.reason = reason;
        }


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

        public String getPosutionN() {
            return posutionN;
        }

        public void setPosutionN(String posutionN) {
            this.posutionN = posutionN;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
