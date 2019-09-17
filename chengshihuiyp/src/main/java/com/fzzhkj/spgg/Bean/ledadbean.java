package com.fzzhkj.spgg.Bean;

import org.litepal.crud.LitePalSupport;

import java.util.List;

public class ledadbean {


    /**
     * status : 1
     * msg : get ok
     * data : [{"id":"3","title":"图片加视频","start":"1558454400","end":"1559934540","img":"http://zmy.0598qq.com/Uploads/Picture/2019-05-30/5ceed8285573d.jpg","video":"http://zmy.0598qq.com/Uploads/Download/2019-05-30/5ceed945e12d7.mp4","status":"1","ord":"0","cate":"3","second":"10"},{"id":"2","title":"视频","start":"1558972800","end":"1559329560","img":"0","video":"http://zmy.0598qq.com/Uploads/Download/2019-05-30/5ceed8cebcb05.mp4","status":"1","ord":"3","cate":"2","second":"10"},{"id":"1","title":"图片","start":"1559059200","end":"1559934300","img":"http://zmy.0598qq.com/Uploads/Picture/2019-05-30/5ceed8285573d.jpg","video":"0","status":"1","ord":"6","cate":"1","second":"10"}]
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

    public static class DataBean extends LitePalSupport {
        /**
         * id : 3
         * title : 图片加视频
         * start : 1558454400
         * end : 1559934540
         * img : http://zmy.0598qq.com/Uploads/Picture/2019-05-30/5ceed8285573d.jpg
         * video : http://zmy.0598qq.com/Uploads/Download/2019-05-30/5ceed945e12d7.mp4
         * status : 1
         * ord : 0
         * cate : 3
         * second : 10
         */

        private String id;
        private String title;
        private String start;
        private String end;
        private String img;
        private String video;
        private String status;
        private String ord;
        private String cate;
        private String second;

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

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrd() {
            return ord;
        }

        public void setOrd(String ord) {
            this.ord = ord;
        }

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        public DataBean(String id, String title, String start, String end, String img, String video, String status, String ord, String cate, String second) {
            this.id = id;
            this.title = title;
            this.start = start;
            this.end = end;
            this.img = img;
            this.video = video;
            this.status = status;
            this.ord = ord;
            this.cate = cate;
            this.second = second;
        }
    }
}
