package com.zhkj.yhfw.Bean;

import java.util.List;

public class TraceInfoBean {

    /**
     * code : 200
     * msg : ok
     * time : 1565346690
     * data : {"counts":1,"tracks":[{"counts":0,"degradedParams":[],"distance":0,"points":[],"time":0,"trid":3800}]}
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
         * counts : 1
         * tracks : [{"counts":0,"degradedParams":[],"distance":0,"points":[],"time":0,"trid":3800}]
         */

        private int counts;
        private List<TracksBean> tracks;

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public List<TracksBean> getTracks() {
            return tracks;
        }

        public void setTracks(List<TracksBean> tracks) {
            this.tracks = tracks;
        }

        public static class TracksBean {
            /**
             * counts : 0
             * degradedParams : []
             * distance : 0
             * points : []
             * time : 0
             * trid : 3800
             */

            private int counts;
            private int distance;
            private int time;
            private int trid;
            private List<?> degradedParams;
            private List<?> points;

            public int getCounts() {
                return counts;
            }

            public void setCounts(int counts) {
                this.counts = counts;
            }

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public int getTime() {
                return time;
            }

            public void setTime(int time) {
                this.time = time;
            }

            public int getTrid() {
                return trid;
            }

            public void setTrid(int trid) {
                this.trid = trid;
            }

            public List<?> getDegradedParams() {
                return degradedParams;
            }

            public void setDegradedParams(List<?> degradedParams) {
                this.degradedParams = degradedParams;
            }

            public List<?> getPoints() {
                return points;
            }

            public void setPoints(List<?> points) {
                this.points = points;
            }
        }
    }
}
