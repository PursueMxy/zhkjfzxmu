package com.zhkj.yhfw.Bean;

import java.util.List;

public class OrderListBean {

    /**
     * code : 200
     * msg : ok
     * time : 1565341027
     * data : {"order":[{"id":221,"type":"0","createtime":1565313715,"startingpoint":"福建省厦门市思明区梧村街道湖滨南路富山花园","destination":"福建省厦门市厦门站","status":"已完成","reason":"","updatetime":1565313731},{"id":220,"type":"","createtime":1565311791,"startingpoint":"厦门市思明区金尚路13","destination":"厦门国贸大厦","status":"已完成","reason":"","updatetime":1565312418},{"id":219,"type":"","createtime":1565311581,"startingpoint":"厦门市湖里区吕岭路684","destination":"厦门国贸大厦","status":"已完成","reason":"","updatetime":1565311767},{"id":218,"type":"0","createtime":1565289132,"startingpoint":"福建省厦门市东百蔡塘广场(蔡塘店)","destination":"福建省厦门市SM新生活广场","status":"已完成","reason":"","updatetime":1565311571},{"id":217,"type":"","createtime":1565288977,"startingpoint":"厦门市湖里区后埔三路105","destination":"厦门市软件园2期","status":"已完成","reason":"","updatetime":1565289026},{"id":216,"type":"","createtime":1565262012,"startingpoint":"厦门市思明区嘉禾路164-101号","destination":"泰和花园(公交站)","status":"已完成","reason":"","updatetime":1565262406},{"id":215,"type":"","createtime":1565261883,"startingpoint":"厦门市思明区嘉禾路67","destination":"泰和花园","status":"已完成","reason":"","updatetime":1565261997},{"id":214,"type":"","createtime":1565261698,"startingpoint":"厦门市思明区湖滨南路813之103号","destination":"泰和花园(公交站)","status":"已完成","reason":"","updatetime":1565261867},{"id":213,"type":"","createtime":1565259252,"startingpoint":"厦门市思明区湖滨南路338号","destination":"厦门北站","status":"已完成","reason":"","updatetime":1565259328},{"id":212,"type":"","createtime":1565259090,"startingpoint":"厦门市思明区湖滨南路338号","destination":"厦门北站(南进站口)","status":"已完成","reason":"","updatetime":1565259244}]}
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
        private List<OrderBean> order;

        public List<OrderBean> getOrder() {
            return order;
        }

        public void setOrder(List<OrderBean> order) {
            this.order = order;
        }

        public static class OrderBean {
            /**
             * id : 221
             * type : 0
             * createtime : 1565313715
             * startingpoint : 福建省厦门市思明区梧村街道湖滨南路富山花园
             * destination : 福建省厦门市厦门站
             * status : 已完成
             * reason :
             * updatetime : 1565313731
             */

            private int id;
            private String type;
            private int createtime;
            private String startingpoint;
            private String destination;
            private String status;
            private String reason;
            private int updatetime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getStartingpoint() {
                return startingpoint;
            }

            public void setStartingpoint(String startingpoint) {
                this.startingpoint = startingpoint;
            }

            public String getDestination() {
                return destination;
            }

            public void setDestination(String destination) {
                this.destination = destination;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getReason() {
                return reason;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
