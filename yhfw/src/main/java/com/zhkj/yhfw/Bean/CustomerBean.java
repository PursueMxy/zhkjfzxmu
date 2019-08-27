package com.zhkj.yhfw.Bean;

import java.util.List;

public class CustomerBean {

    /**
     * code : 200
     * msg : 获取成功
     * time : 1565934607
     * data : [{"id":18,"name":"123","user_mobile":"18756070310","mobile":"12345678985","createtime":1565596705,"updatetime":null,"deletetime":null}]
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
         * id : 18
         * name : 123
         * user_mobile : 18756070310
         * mobile : 12345678985
         * createtime : 1565596705
         * updatetime : null
         * deletetime : null
         */

        private int id;
        private String name;
        private String user_mobile;
        private String mobile;
        private int createtime;
        private int updatetime;
        private int deletetime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser_mobile() {
            return user_mobile;
        }

        public void setUser_mobile(String user_mobile) {
            this.user_mobile = user_mobile;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public int getDeletetime() {
            return deletetime;
        }

        public void setDeletetime(int deletetime) {
            this.deletetime = deletetime;
        }
    }
}
