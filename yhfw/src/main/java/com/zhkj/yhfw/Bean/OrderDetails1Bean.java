package com.zhkj.yhfw.Bean;

import java.util.List;

public class OrderDetails1Bean {

    /**
     * code : 200
     * msg : OK
     * time : 1567058559
     * data : {"id":249,"type":"","driver_id":3,"user_id":null,"startingpointE":"24.613285","startingpointN":"118.04778","startingpoint":"厦门市集美区诚毅北大街20","destinationE":"24.572899","destinationN":"118.073177","destination":"园博园","status":"6","departuretime":1565672209,"estimatedeparturetime":0,"createtime":1565672164,"updatetime":1565672248,"endtime":1565672248,"meal_id":0,"driver_arrivaltime":1565672204,"arrivaltime":1565672246,"money":"19.00","start_money":"19.00","mileage_money":"0.00","return_money":"0.00","travel_time":37,"wait_time":5,"wait_money":"0.00","mileage":"0","city":"","estimated_mileage":0,"coupon_id":null,"paymoney":"19.00","mobile":"18756070310","order_trip_id":941,"budget":null,"service_type":"市内服务","createddatetime":"12:56:04","pay_type":"线下支付","commission_platform":"4.09","reason":"","estimated_time":0,"driverInfo":{"nickname":"肖振玉  测试账户","drivingage":1,"mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","id":3,"driving_license_time":958437205},"userInfo":[],"info_submited":0,"score":null}
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
         * id : 249
         * type :
         * driver_id : 3
         * user_id : null
         * startingpointE : 24.613285
         * startingpointN : 118.04778
         * startingpoint : 厦门市集美区诚毅北大街20
         * destinationE : 24.572899
         * destinationN : 118.073177
         * destination : 园博园
         * status : 6
         * departuretime : 1565672209
         * estimatedeparturetime : 0
         * createtime : 1565672164
         * updatetime : 1565672248
         * endtime : 1565672248
         * meal_id : 0
         * driver_arrivaltime : 1565672204
         * arrivaltime : 1565672246
         * money : 19.00
         * start_money : 19.00
         * mileage_money : 0.00
         * return_money : 0.00
         * travel_time : 37
         * wait_time : 5
         * wait_money : 0.00
         * mileage : 0
         * city :
         * estimated_mileage : 0
         * coupon_id : null
         * paymoney : 19.00
         * mobile : 18756070310
         * order_trip_id : 941
         * budget : null
         * service_type : 市内服务
         * createddatetime : 12:56:04
         * pay_type : 线下支付
         * commission_platform : 4.09
         * reason :
         * estimated_time : 0
         * driverInfo : {"nickname":"肖振玉  测试账户","drivingage":1,"mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","id":3,"driving_license_time":958437205}
         * userInfo : []
         * info_submited : 0
         * score : null
         */

        private int id;
        private String type;
        private int driver_id;
        private Object user_id;
        private String startingpointE;
        private String startingpointN;
        private String startingpoint;
        private String destinationE;
        private String destinationN;
        private String destination;
        private String status;
        private int departuretime;
        private int estimatedeparturetime;
        private int createtime;
        private int updatetime;
        private int endtime;
        private int meal_id;
        private int driver_arrivaltime;
        private int arrivaltime;
        private String money;
        private String start_money;
        private String mileage_money;
        private String return_money;
        private int travel_time;
        private int wait_time;
        private String wait_money;
        private String mileage;
        private String city;
        private int estimated_mileage;
        private Object coupon_id;
        private String paymoney;
        private String mobile;
        private int order_trip_id;
        private Object budget;
        private String service_type;
        private String createddatetime;
        private String pay_type;
        private String commission_platform;
        private String reason;
        private int estimated_time;
        private DriverInfoBean driverInfo;
        private int info_submited;
        private Object score;
        private List<?> userInfo;

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

        public int getDriver_id() {
            return driver_id;
        }

        public void setDriver_id(int driver_id) {
            this.driver_id = driver_id;
        }

        public Object getUser_id() {
            return user_id;
        }

        public void setUser_id(Object user_id) {
            this.user_id = user_id;
        }

        public String getStartingpointE() {
            return startingpointE;
        }

        public void setStartingpointE(String startingpointE) {
            this.startingpointE = startingpointE;
        }

        public String getStartingpointN() {
            return startingpointN;
        }

        public void setStartingpointN(String startingpointN) {
            this.startingpointN = startingpointN;
        }

        public String getStartingpoint() {
            return startingpoint;
        }

        public void setStartingpoint(String startingpoint) {
            this.startingpoint = startingpoint;
        }

        public String getDestinationE() {
            return destinationE;
        }

        public void setDestinationE(String destinationE) {
            this.destinationE = destinationE;
        }

        public String getDestinationN() {
            return destinationN;
        }

        public void setDestinationN(String destinationN) {
            this.destinationN = destinationN;
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

        public int getDeparturetime() {
            return departuretime;
        }

        public void setDeparturetime(int departuretime) {
            this.departuretime = departuretime;
        }

        public int getEstimatedeparturetime() {
            return estimatedeparturetime;
        }

        public void setEstimatedeparturetime(int estimatedeparturetime) {
            this.estimatedeparturetime = estimatedeparturetime;
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

        public int getEndtime() {
            return endtime;
        }

        public void setEndtime(int endtime) {
            this.endtime = endtime;
        }

        public int getMeal_id() {
            return meal_id;
        }

        public void setMeal_id(int meal_id) {
            this.meal_id = meal_id;
        }

        public int getDriver_arrivaltime() {
            return driver_arrivaltime;
        }

        public void setDriver_arrivaltime(int driver_arrivaltime) {
            this.driver_arrivaltime = driver_arrivaltime;
        }

        public int getArrivaltime() {
            return arrivaltime;
        }

        public void setArrivaltime(int arrivaltime) {
            this.arrivaltime = arrivaltime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStart_money() {
            return start_money;
        }

        public void setStart_money(String start_money) {
            this.start_money = start_money;
        }

        public String getMileage_money() {
            return mileage_money;
        }

        public void setMileage_money(String mileage_money) {
            this.mileage_money = mileage_money;
        }

        public String getReturn_money() {
            return return_money;
        }

        public void setReturn_money(String return_money) {
            this.return_money = return_money;
        }

        public int getTravel_time() {
            return travel_time;
        }

        public void setTravel_time(int travel_time) {
            this.travel_time = travel_time;
        }

        public int getWait_time() {
            return wait_time;
        }

        public void setWait_time(int wait_time) {
            this.wait_time = wait_time;
        }

        public String getWait_money() {
            return wait_money;
        }

        public void setWait_money(String wait_money) {
            this.wait_money = wait_money;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getEstimated_mileage() {
            return estimated_mileage;
        }

        public void setEstimated_mileage(int estimated_mileage) {
            this.estimated_mileage = estimated_mileage;
        }

        public Object getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(Object coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(String paymoney) {
            this.paymoney = paymoney;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getOrder_trip_id() {
            return order_trip_id;
        }

        public void setOrder_trip_id(int order_trip_id) {
            this.order_trip_id = order_trip_id;
        }

        public Object getBudget() {
            return budget;
        }

        public void setBudget(Object budget) {
            this.budget = budget;
        }

        public String getService_type() {
            return service_type;
        }

        public void setService_type(String service_type) {
            this.service_type = service_type;
        }

        public String getCreateddatetime() {
            return createddatetime;
        }

        public void setCreateddatetime(String createddatetime) {
            this.createddatetime = createddatetime;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getCommission_platform() {
            return commission_platform;
        }

        public void setCommission_platform(String commission_platform) {
            this.commission_platform = commission_platform;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getEstimated_time() {
            return estimated_time;
        }

        public void setEstimated_time(int estimated_time) {
            this.estimated_time = estimated_time;
        }

        public DriverInfoBean getDriverInfo() {
            return driverInfo;
        }

        public void setDriverInfo(DriverInfoBean driverInfo) {
            this.driverInfo = driverInfo;
        }

        public int getInfo_submited() {
            return info_submited;
        }

        public void setInfo_submited(int info_submited) {
            this.info_submited = info_submited;
        }

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public List<?> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<?> userInfo) {
            this.userInfo = userInfo;
        }

        public static class DriverInfoBean {
            /**
             * nickname : 肖振玉  测试账户
             * drivingage : 1
             * mobile : 18859187133
             * avatar : /uploads/tx/20190715190759_.jpeg
             * id : 3
             * driving_license_time : 958437205
             */

            private String nickname;
            private int drivingage;
            private String mobile;
            private String avatar;
            private int id;
            private int driving_license_time;

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

            public int getDriving_license_time() {
                return driving_license_time;
            }

            public void setDriving_license_time(int driving_license_time) {
                this.driving_license_time = driving_license_time;
            }
        }
    }
}
