package com.zhkj.yhfw.Bean;

public class points {

    /**
     * location : 116.397428,39.90923
     * locatetime : 1544176895000
     * speed : 40
     * direction : 120
     * height : 39
     * accuracy : 20
     */

    private String location;
    private long locatetime;
    private int speed;
    private int direction;
    private int height;
    private int accuracy;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getLocatetime() {
        return locatetime;
    }

    public void setLocatetime(long locatetime) {
        this.locatetime = locatetime;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public points(String location, long locatetime, int speed, int direction, int height, int accuracy) {
        this.location = location;
        this.locatetime = locatetime;
        this.speed = speed;
        this.direction = direction;
        this.height = height;
        this.accuracy = accuracy;
    }
}
