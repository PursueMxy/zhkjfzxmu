package com.zhkj.yhfw.Bean;

import java.util.List;

public class PointsBean {
    private String key;
    private String sid;
    private String tid;
    private String trid;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTrid() {
        return trid;
    }

    public void setTrid(String trid) {
        this.trid = trid;
    }

    public List<points> points;

    public List<PointsBean.points> getPoints() {
        return points;
    }

    public void setPoints(List<PointsBean.points> points) {
        this.points = points;
    }

    public PointsBean(List<PointsBean.points> points) {
        this.points = points;
    }

    public PointsBean(String key, String sid, String tid, String trid, List<PointsBean.points> points) {
        this.key = key;
        this.sid = sid;
        this.tid = tid;
        this.trid = trid;
        this.points = points;
    }

    public static class points {
        private String location;
        private long locatetime;
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

        public int getAccuracy() {
            return accuracy;
        }

        public void setAccuracy(int accuracy) {
            this.accuracy = accuracy;
        }

        public points(String location, long locatetime, int accuracy) {
            this.location = location;
            this.locatetime = locatetime;
            this.accuracy = accuracy;
        }
    }
}
