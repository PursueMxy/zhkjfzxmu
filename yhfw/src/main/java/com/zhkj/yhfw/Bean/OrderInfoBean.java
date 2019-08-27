package com.zhkj.yhfw.Bean;

public class OrderInfoBean {

    /**
     * event : order_info
     * code : 200
     * msg : 获取成功
     * data : {"order":{"id":151,"type":"","driver_id":3,"user_id":null,"startingpointE":"24.476565483940973","startingpointN":"118.11848253038194","startingpoint":"厦门市思明区湖滨南路338号","destinationE":"24.533671","destinationN":"118.180859","destination":"五缘湾帆船港","status":"2","departuretime":null,"estimatedeparturetime":0,"createtime":1565059400,"updatetime":1565059400,"endtime":null,"meal_id":0,"driver_arrivaltime":null,"arrivaltime":null,"money":null,"start_money":null,"mileage_money":null,"return_money":null,"travel_time":0,"wait_time":0,"wait_money":null,"mileage":null,"city":"","estimated_mileage":0,"coupon_id":null,"paymoney":null,"mobile":"18859187133","order_trip_id":843,"budget":null,"service_type":"市内服务","createddatetime":"10:43:20","pay_type":"","commission_platform":"0.00","reason":"","estimated_time":0},"trip":{"id":843,"trace_id":0,"order_id":151,"startingpoint":"厦门市思明区湖滨南路338号","destination":"五缘湾帆船港","startingpointE":"24.476565483940973","startingpointN":"118.11848253038194","destinationN":"118.180859","mileage":null,"destinationE":"24.533671","route":null,"createtime":1565059400,"updatetime":null,"sid":0,"tid":0},"driver":{"id":3,"nickname":"肖振玉  测试账户","mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","gender":1,"birthday":null,"money":"32.90","balance":"0.00","score":0,"token":"8ddce081-5ce9-4a68-8a3a-3a85562c4e87","status":"normal","verification":"","openid":null,"VIP":null,"fid":0,"commission":"0.00","fprofit":"0.00","QRcodeexpirestime":0,"order_trip_id":843}}
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
         * order : {"id":151,"type":"","driver_id":3,"user_id":null,"startingpointE":"24.476565483940973","startingpointN":"118.11848253038194","startingpoint":"厦门市思明区湖滨南路338号","destinationE":"24.533671","destinationN":"118.180859","destination":"五缘湾帆船港","status":"2","departuretime":null,"estimatedeparturetime":0,"createtime":1565059400,"updatetime":1565059400,"endtime":null,"meal_id":0,"driver_arrivaltime":null,"arrivaltime":null,"money":null,"start_money":null,"mileage_money":null,"return_money":null,"travel_time":0,"wait_time":0,"wait_money":null,"mileage":null,"city":"","estimated_mileage":0,"coupon_id":null,"paymoney":null,"mobile":"18859187133","order_trip_id":843,"budget":null,"service_type":"市内服务","createddatetime":"10:43:20","pay_type":"","commission_platform":"0.00","reason":"","estimated_time":0}
         * trip : {"id":843,"trace_id":0,"order_id":151,"startingpoint":"厦门市思明区湖滨南路338号","destination":"五缘湾帆船港","startingpointE":"24.476565483940973","startingpointN":"118.11848253038194","destinationN":"118.180859","mileage":null,"destinationE":"24.533671","route":null,"createtime":1565059400,"updatetime":null,"sid":0,"tid":0}
         * driver : {"id":3,"nickname":"肖振玉  测试账户","mobile":"18859187133","avatar":"/uploads/tx/20190715190759_.jpeg","gender":1,"birthday":null,"money":"32.90","balance":"0.00","score":0,"token":"8ddce081-5ce9-4a68-8a3a-3a85562c4e87","status":"normal","verification":"","openid":null,"VIP":null,"fid":0,"commission":"0.00","fprofit":"0.00","QRcodeexpirestime":0,"order_trip_id":843}
         */

        private OrderBean order;
        private TripBean trip;
        private DriverBean driver;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public TripBean getTrip() {
            return trip;
        }

        public void setTrip(TripBean trip) {
            this.trip = trip;
        }

        public DriverBean getDriver() {
            return driver;
        }

        public void setDriver(DriverBean driver) {
            this.driver = driver;
        }

        public static class OrderBean {
            /**
             * id : 151
             * type : 
             * driver_id : 3
             * user_id : null
             * startingpointE : 24.476565483940973
             * startingpointN : 118.11848253038194
             * startingpoint : 厦门市思明区湖滨南路338号
             * destinationE : 24.533671
             * destinationN : 118.180859
             * destination : 五缘湾帆船港
             * status : 2
             * departuretime : null
             * estimatedeparturetime : 0
             * createtime : 1565059400
             * updatetime : 1565059400
             * endtime : null
             * meal_id : 0
             * driver_arrivaltime : null
             * arrivaltime : null
             * money : null
             * start_money : null
             * mileage_money : null
             * return_money : null
             * travel_time : 0
             * wait_time : 0
             * wait_money : null
             * mileage : null
             * city : 
             * estimated_mileage : 0
             * coupon_id : null
             * paymoney : null
             * mobile : 18859187133
             * order_trip_id : 843
             * budget : null
             * service_type : 市内服务
             * createddatetime : 10:43:20
             * pay_type : 
             * commission_platform : 0.00
             * reason : 
             * estimated_time : 0
             */

            private int id;
            private String type;
            private int driver_id;
            private String user_id;
            private String startingpointE;
            private String startingpointN;
            private String startingpoint;
            private String destinationE;
            private String destinationN;
            private String destination;
            private String status;
            private String departuretime;
            private int estimatedeparturetime;
            private int createtime;
            private int updatetime;
            private String endtime;
            private int meal_id;
            private String driver_arrivaltime;
            private String arrivaltime;
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
            private String coupon_id;
            private String paymoney;
            private String mobile;
            private int order_trip_id;
            private String budget;
            private String service_type;
            private String createddatetime;
            private String pay_type;
            private String commission_platform;
            private String reason;
            private int estimated_time;

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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
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

            public String getDeparturetime() {
                return departuretime;
            }

            public void setDeparturetime(String departuretime) {
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

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public int getMeal_id() {
                return meal_id;
            }

            public void setMeal_id(int meal_id) {
                this.meal_id = meal_id;
            }

            public String getDriver_arrivaltime() {
                return driver_arrivaltime;
            }

            public void setDriver_arrivaltime(String driver_arrivaltime) {
                this.driver_arrivaltime = driver_arrivaltime;
            }

            public String getArrivaltime() {
                return arrivaltime;
            }

            public void setArrivaltime(String arrivaltime) {
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

            public String getCoupon_id() {
                return coupon_id;
            }

            public void setCoupon_id(String coupon_id) {
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

            public String getBudget() {
                return budget;
            }

            public void setBudget(String budget) {
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
        }

        public static class TripBean {
            /**
             * id : 843
             * trace_id : 0
             * order_id : 151
             * startingpoint : 厦门市思明区湖滨南路338号
             * destination : 五缘湾帆船港
             * startingpointE : 24.476565483940973
             * startingpointN : 118.11848253038194
             * destinationN : 118.180859
             * mileage : null
             * destinationE : 24.533671
             * route : null
             * createtime : 1565059400
             * updatetime : null
             * sid : 0
             * tid : 0
             */

            private int id;
            private int trace_id;
            private int order_id;
            private String startingpoint;
            private String destination;
            private String startingpointE;
            private String startingpointN;
            private String destinationN;
            private String mileage;
            private String destinationE;
            private String route;
            private int createtime;
            private String updatetime;
            private int sid;
            private int tid;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTrace_id() {
                return trace_id;
            }

            public void setTrace_id(int trace_id) {
                this.trace_id = trace_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
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

            public String getDestinationN() {
                return destinationN;
            }

            public void setDestinationN(String destinationN) {
                this.destinationN = destinationN;
            }

            public String getMileage() {
                return mileage;
            }

            public void setMileage(String mileage) {
                this.mileage = mileage;
            }

            public String getDestinationE() {
                return destinationE;
            }

            public void setDestinationE(String destinationE) {
                this.destinationE = destinationE;
            }

            public String getRoute() {
                return route;
            }

            public void setRoute(String route) {
                this.route = route;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public int getSid() {
                return sid;
            }

            public void setSid(int sid) {
                this.sid = sid;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }
        }

        public static class DriverBean {
            /**
             * id : 3
             * nickname : 肖振玉  测试账户
             * mobile : 18859187133
             * avatar : /uploads/tx/20190715190759_.jpeg
             * gender : 1
             * birthday : null
             * money : 32.90
             * balance : 0.00
             * score : 0
             * token : 8ddce081-5ce9-4a68-8a3a-3a85562c4e87
             * status : normal
             * verification : 
             * openid : null
             * VIP : null
             * fid : 0
             * commission : 0.00
             * fprofit : 0.00
             * QRcodeexpirestime : 0
             * order_trip_id : 843
             */

            private int id;
            private String nickname;
            private String mobile;
            private String avatar;
            private int gender;
            private String birthday;
            private String money;
            private String balance;
            private int score;
            private String token;
            private String status;
            private String verification;
            private String openid;
            private String VIP;
            private int fid;
            private String commission;
            private String fprofit;
            private int QRcodeexpirestime;
            private int order_trip_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
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

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getVerification() {
                return verification;
            }

            public void setVerification(String verification) {
                this.verification = verification;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getVIP() {
                return VIP;
            }

            public void setVIP(String VIP) {
                this.VIP = VIP;
            }

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public String getCommission() {
                return commission;
            }

            public void setCommission(String commission) {
                this.commission = commission;
            }

            public String getFprofit() {
                return fprofit;
            }

            public void setFprofit(String fprofit) {
                this.fprofit = fprofit;
            }

            public int getQRcodeexpirestime() {
                return QRcodeexpirestime;
            }

            public void setQRcodeexpirestime(int QRcodeexpirestime) {
                this.QRcodeexpirestime = QRcodeexpirestime;
            }

            public int getOrder_trip_id() {
                return order_trip_id;
            }

            public void setOrder_trip_id(int order_trip_id) {
                this.order_trip_id = order_trip_id;
            }
        }
    }
}
