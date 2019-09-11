package com.fzzhkj.spgg;

import java.util.List;

public class subtitle {

    /**
     * status : 1
     * msg : 获取字幕成功
     * data : [{"id":"1","title":"电子屏上线测试啦 ","color":"#FFFFFF","theaccount":"1","dateline":"1564391280","status":"1"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 电子屏上线测试啦
         * color : #FFFFFF
         * theaccount : 1
         * dateline : 1564391280
         * status : 1
         */

        private String id;
        private String title;
        private String color;
        private String theaccount;
        private String dateline;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getTheaccount() {
            return theaccount;
        }

        public void setTheaccount(String theaccount) {
            this.theaccount = theaccount;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
