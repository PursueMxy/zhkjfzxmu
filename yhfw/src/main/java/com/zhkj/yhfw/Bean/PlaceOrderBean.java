package com.zhkj.yhfw.Bean;

import java.security.PublicKey;
import java.util.List;

public class PlaceOrderBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public PlaceOrderBean.data getData() {
        return data;
    }

    public void setData(PlaceOrderBean.data data) {
        this.data = data;
    }

    public PlaceOrderBean(String event, String token, String type, String send, PlaceOrderBean.data data) {
        this.event = event;
        this.token = token;
        this.type = type;
        this.send = send;
        this.data = data;
    }

    public static  class  data{
        public String driver_type;
        public String mobile;
        public String startingpointE;
        public String startingpointN;

        public  String startingpoint;
        public  String destinationE;
        public  String destinationN;
        public  String destination;
        public  String estimatedeparturetime;


        public String getDriver_type() {
            return driver_type;
        }

        public void setDriver_type(String driver_type) {
            this.driver_type = driver_type;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }


        public String getStartingpointE() {
            return startingpointE;
        }

        public void setStartingpointE(String startingpointE) {
            this.startingpointE = startingpointE;
        }

        public String getStartingpointN() {
            return startingpointN;
        }

        public void setStartingpointN(String startingpointN) {
            this.startingpointN = startingpointN;
        }

        public String getStartingpoint() {
            return startingpoint;
        }

        public void setStartingpoint(String startingpoint) {
            this.startingpoint = startingpoint;
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

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(String estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
        }

        public data(String driver_type, String mobile, String startingpointE, String startingpointN, String startingpoint, String destinationE, String destinationN, String destination, String estimatedeparturetime) {
            this.driver_type = driver_type;
            this.mobile = mobile;
            this.startingpointE = startingpointE;
            this.startingpointN = startingpointN;
            this.startingpoint = startingpoint;
            this.destinationE = destinationE;
            this.destinationN = destinationN;
            this.destination = destination;
            this.estimatedeparturetime = estimatedeparturetime;
        }
    }


}
