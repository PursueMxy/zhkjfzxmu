package com.zhkj.yhfw.Bean;

public class CrossedPathsOrderBean {
    public String event;
    public String token;
    public  String type;
    public String send;
    public data data;


    public CrossedPathsOrderBean(String event, String token, String type, String send, CrossedPathsOrderBean.data data) {
        this.event = event;
        this.token = token;
        this.type = type;
        this.send = send;
        this.data = data;
    }

    public CrossedPathsOrderBean.data getData() {
        return data;
    }

    public void setData(CrossedPathsOrderBean.data data) {
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



    public static class  data {
        public String city;
        public int drive_type;
        public String estimatedeparturetime;
        public String meal_id;
        public String position;
        public String positionE;
        public String positionN;
        public String service_type;
        public String startingpoint;
        public String startingpointE;
        public String startingpointN;
        public String status;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getDrive_type() {
            return drive_type;
        }

        public void setDrive_type(int drive_type) {
            this.drive_type = drive_type;
        }

        public String getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(String estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
        }

        public String getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(String meal_id) {
            this.meal_id = meal_id;
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

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getStartingpoint() {
            return startingpoint;
        }

        public void setStartingpoint(String startingpoint) {
            this.startingpoint = startingpoint;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public data(String city, int drive_type, String estimatedeparturetime, String meal_id, String position, String positionE, String positionN, String service_type, String startingpoint, String startingpointE, String startingpointN, String status) {
            this.city = city;
            this.drive_type = drive_type;
            this.estimatedeparturetime = estimatedeparturetime;
            this.meal_id = meal_id;
            this.position = position;
            this.positionE = positionE;
            this.positionN = positionN;
            this.service_type = service_type;
            this.startingpoint = startingpoint;
            this.startingpointE = startingpointE;
            this.startingpointN = startingpointN;
            this.status = status;
        }
    }
}
