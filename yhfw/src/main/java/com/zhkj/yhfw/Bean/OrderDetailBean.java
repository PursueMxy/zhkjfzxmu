package com.zhkj.yhfw.Bean;

public class OrderDetailBean {

    /**
     * event : arrive
     * code : 200
     * msg : 到达目的地
     * data : {"money":93,"start_price":"19","input_mileage":0,"return_money":0,"run_time":4,"wait_time":1400,"wait_money":74,"driverInfo":{"nickname":"肖振玉  测试账户","drivingage":1,"mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","id":3},"startingpoint":"福建省厦门市思明区梧村街道湖滨南路334号二轻大厦(湖滨南路)","destination":"福建省厦门市厦门大学思明校区"}
     */

    private String event;
    private int code;
    private String msg;
    private DataBean data;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * money : 93
         * start_price : 19
         * input_mileage : 0
         * return_money : 0
         * run_time : 4
         * wait_time : 1400
         * wait_money : 74
         * driverInfo : {"nickname":"肖振玉  测试账户","drivingage":1,"mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","id":3}
         * startingpoint : 福建省厦门市思明区梧村街道湖滨南路334号二轻大厦(湖滨南路)
         * destination : 福建省厦门市厦门大学思明校区
         */

        private int money;
        private String start_price;
        private int input_mileage;
        private int return_money;
        private int run_time;
        private int wait_time;
        private int wait_money;
        private DriverInfoBean driverInfo;
        private String startingpoint;
        private String destination;

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getStart_price() {
            return start_price;
        }

        public void setStart_price(String start_price) {
            this.start_price = start_price;
        }

        public int getInput_mileage() {
            return input_mileage;
        }

        public void setInput_mileage(int input_mileage) {
            this.input_mileage = input_mileage;
        }

        public int getReturn_money() {
            return return_money;
        }

        public void setReturn_money(int return_money) {
            this.return_money = return_money;
        }

        public int getRun_time() {
            return run_time;
        }

        public void setRun_time(int run_time) {
            this.run_time = run_time;
        }

        public int getWait_time() {
            return wait_time;
        }

        public void setWait_time(int wait_time) {
            this.wait_time = wait_time;
        }

        public int getWait_money() {
            return wait_money;
        }

        public void setWait_money(int wait_money) {
            this.wait_money = wait_money;
        }

        public DriverInfoBean getDriverInfo() {
            return driverInfo;
        }

        public void setDriverInfo(DriverInfoBean driverInfo) {
            this.driverInfo = driverInfo;
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

        public static class DriverInfoBean {
            /**
             * nickname : 肖振玉  测试账户
             * drivingage : 1
             * mobile : 18859187133
             * avatar : /uploads/tx/20190715190759_.jpeg
             * id : 3
             */

            private String nickname;
            private int drivingage;
            private String mobile;
            private String avatar;
            private int id;

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public int getDrivingage() {
                return drivingage;
            }

            public void setDrivingage(int drivingage) {
                this.drivingage = drivingage;
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

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
