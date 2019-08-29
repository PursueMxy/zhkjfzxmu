package com.zhkj.yhfw.Bean;

import java.util.List;

public class RecordingBean {

    /**
     * code : 200
     * msg : 获取成功
     * time : 1566974395
     * data : [{"id":81,"user_id":3,"name":"1566969461","path":"/uploads/20190828/9fed3eaea7922bba209ba0983c4293b2.amr","user_mobile":"18859187133","duration":null,"createtime":1566969461,"deletetime":null,"type":1}]
     */

    private int code;
    private String msg;
    private String time;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 81
         * user_id : 3
         * name : 1566969461
         * path : /uploads/20190828/9fed3eaea7922bba209ba0983c4293b2.amr
         * user_mobile : 18859187133
         * duration : null
         * createtime : 1566969461
         * deletetime : null
         * type : 1
         */

        private int id;
        private int user_id;
        private String name;
        private String path;
        private String user_mobile;
        private Object duration;
        private int createtime;
        private Object deletetime;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public Object getDuration() {
            return duration;
        }

        public void setDuration(Object duration) {
            this.duration = duration;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public Object getDeletetime() {
            return deletetime;
        }

        public void setDeletetime(Object deletetime) {
            this.deletetime = deletetime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
