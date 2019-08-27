package com.zhkj.yhfw.Bean;

public class UserInfoBean {

    /**
     * code : 200
     * msg : Logged in successful
     * time : 1565319993
     * data : {"userinfo":{"id":3,"username":"","nickname":"肖振玉  测试账户","mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","money":"9668.38","balance":"0.00","score":0,"openid":null,"drivingage":1,"type":"1","VIP":null,"onlinetime":128722,"driving_license_time":958437205,"token":"f6dc3097-1dd6-44ad-b885-a1ec8d5fdfff","user_id":3,"createtime":1565319990,"expiretime":1567911990,"expires_in":2591997,"count":70}}
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
         * userinfo : {"id":3,"username":"","nickname":"肖振玉  测试账户","mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","money":"9668.38","balance":"0.00","score":0,"openid":null,"drivingage":1,"type":"1","VIP":null,"onlinetime":128722,"driving_license_time":958437205,"token":"f6dc3097-1dd6-44ad-b885-a1ec8d5fdfff","user_id":3,"createtime":1565319990,"expiretime":1567911990,"expires_in":2591997,"count":70}
         */

        private UserinfoBean userinfo;

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserinfoBean {
            /**
             * id : 3
             * username :
             * nickname : 肖振玉  测试账户
             * mobile : 18859187133
             * avatar : /uploads/tx/20190715190759_.jpeg
             * money : 9668.38
             * balance : 0.00
             * score : 0
             * openid : null
             * drivingage : 1
             * type : 1
             * VIP : null
             * onlinetime : 128722
             * driving_license_time : 958437205
             * token : f6dc3097-1dd6-44ad-b885-a1ec8d5fdfff
             * user_id : 3
             * createtime : 1565319990
             * expiretime : 1567911990
             * expires_in : 2591997
             * count : 70
             */

            private int id;
            private String username;
            private String nickname;
            private String mobile;
            private String avatar;
            private String money;
            private String balance;
            private int score;
            private Object openid;
            private int drivingage;
            private String type;
            private Object VIP;
            private int onlinetime;
            private int driving_license_time;
            private String token;
            private int user_id;
            private int createtime;
            private int expiretime;
            private int expires_in;
            private int count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
                this.openid = openid;
            }

            public int getDrivingage() {
                return drivingage;
            }

            public void setDrivingage(int drivingage) {
                this.drivingage = drivingage;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getVIP() {
                return VIP;
            }

            public void setVIP(Object VIP) {
                this.VIP = VIP;
            }

            public int getOnlinetime() {
                return onlinetime;
            }

            public void setOnlinetime(int onlinetime) {
                this.onlinetime = onlinetime;
            }

            public int getDriving_license_time() {
                return driving_license_time;
            }

            public void setDriving_license_time(int driving_license_time) {
                this.driving_license_time = driving_license_time;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public int getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(int expiretime) {
                this.expiretime = expiretime;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
