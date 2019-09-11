package com.fzzhkj.spgg;

import java.util.List;

public class subtitle {


    /**
     * status : 1
     * starttime : null
     * liveflag : null
     * msg : 获取字幕成功
     * data : [{"id":"0","title":"666","color":"","theaccount":"124288","dateline":"1568183514","status":"1","nickname":"宋起文"}]
     */

    private int status;
    private Object starttime;
    private Object liveflag;
    private String msg;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getStarttime() {
        return starttime;
    }

    public void setStarttime(Object starttime) {
        this.starttime = starttime;
    }

    public Object getLiveflag() {
        return liveflag;
    }

    public void setLiveflag(Object liveflag) {
        this.liveflag = liveflag;
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
         * id : 0
         * title : 666
         * color :
         * theaccount : 124288
         * dateline : 1568183514
         * status : 1
         * nickname : 宋起文
         */

        private String id;
        private String title;
        private String color;
        private String theaccount;
        private String dateline;
        private String status;
        private String nickname;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
