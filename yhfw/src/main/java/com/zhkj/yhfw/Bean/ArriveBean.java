package com.zhkj.yhfw.Bean;

import java.security.PublicKey;

//到达终点
public class ArriveBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;

    public ArriveBean(String event, String token, String type, String send, ArriveBean.data data) {
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

    public ArriveBean.data getData() {
        return data;
    }

    public void setData(ArriveBean.data data) {
        this.data = data;
    }

    public static class data{
        public  String city;
        public String destination;
        public String destinationE;
        public String destinationN;
        public String mileage;
        public int order_id;
        public String position;
        public String positionE;
        public String positionN;

        public data(String city, String destination, String destinationE, String destinationN, String mileage, int order_id, String position, String positionE, String positionN) {
            this.city = city;
            this.destination = destination;
            this.destinationE = destinationE;
            this.destinationN = destinationN;
            this.mileage = mileage;
            this.order_id = order_id;
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

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getDestinationE() {
            return destinationE;
        }

        public void setDestinationE(String destinationE) {
            this.destinationE = destinationE;
        }

        public String getDestinationN() {
            return destinationN;
        }

        public void setDestinationN(String destinationN) {
            this.destinationN = destinationN;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
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
            this.positionN = positionN;
        }
    }
}
