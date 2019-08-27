package com.zhkj.yhfw.Bean;

import java.util.List;

public class OrderHallBean {

    /**
     * code : 200
     * msg : 获取成功
     * time : 1565603863
     * data : {"list":[{"id":239,"type":"0","driver_id":null,"user_id":176,"startingpointE":"119.2783203125","startingpointN":"26.060575485229","startingpoint":"福建省福州市台江区宁化街道尚文路18号富力中心","destinationE":"119.225276","destinationN":"26.022033","destination":"福建省福州市碧桂园十里江湾(建设中)","status":"1","departuretime":null,"estimatedeparturetime":0,"createtime":1565603468,"updatetime":1565603468,"endtime":null,"meal_id":0,"driver_arrivaltime":null,"arrivaltime":null,"money":null,"start_money":null,"mileage_money":null,"return_money":null,"travel_time":0,"wait_time":0,"wait_money":null,"mileage":null,"city":"福州市","estimated_mileage":11,"coupon_id":null,"paymoney":null,"mobile":"18859187133","order_trip_id":931,"budget":null,"service_type":"","createddatetime":"17:51:08","pay_type":"","commission_platform":"0.00","reason":"","estimated_time":1594,"distance":12905405,"VIP":"0"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 239
             * type : 0
             * driver_id : null
             * user_id : 176
             * startingpointE : 119.2783203125
             * startingpointN : 26.060575485229
             * startingpoint : 福建省福州市台江区宁化街道尚文路18号富力中心
             * destinationE : 119.225276
             * destinationN : 26.022033
             * destination : 福建省福州市碧桂园十里江湾(建设中)
             * status : 1
             * departuretime : null
             * estimatedeparturetime : 0
             * createtime : 1565603468
             * updatetime : 1565603468
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
             * city : 福州市
             * estimated_mileage : 11
             * coupon_id : null
             * paymoney : null
             * mobile : 18859187133
             * order_trip_id : 931
             * budget : null
             * service_type :
             * createddatetime : 17:51:08
             * pay_type :
             * commission_platform : 0.00
             * reason :
             * estimated_time : 1594
             * distance : 12905405
             * VIP : 0
             */

            private int id;
            private String type;
            private Object driver_id;
            private int user_id;
            private String startingpointE;
            private String startingpointN;
            private String startingpoint;
            private String destinationE;
            private String destinationN;
            private String destination;
            private String status;
            private Object departuretime;
            private int estimatedeparturetime;
            private int createtime;
            private int updatetime;
            private Object endtime;
            private int meal_id;
            private Object driver_arrivaltime;
            private Object arrivaltime;
            private Object money;
            private Object start_money;
            private Object mileage_money;
            private Object return_money;
            private int travel_time;
            private int wait_time;
            private Object wait_money;
            private Object mileage;
            private String city;
            private int estimated_mileage;
            private Object coupon_id;
            private Object paymoney;
            private String mobile;
            private int order_trip_id;
            private Object budget;
            private String service_type;
            private String createddatetime;
            private String pay_type;
            private String commission_platform;
            private String reason;
            private int estimated_time;
            private int distance;
            private String VIP;

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

            public Object getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(Object driver_id) {
                this.driver_id = driver_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
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

            public Object getDeparturetime() {
                return departuretime;
            }

            public void setDeparturetime(Object departuretime) {
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

            public Object getEndtime() {
                return endtime;
            }

            public void setEndtime(Object endtime) {
                this.endtime = endtime;
            }

            public int getMeal_id() {
                return meal_id;
            }

            public void setMeal_id(int meal_id) {
                this.meal_id = meal_id;
            }

            public Object getDriver_arrivaltime() {
                return driver_arrivaltime;
            }

            public void setDriver_arrivaltime(Object driver_arrivaltime) {
                this.driver_arrivaltime = driver_arrivaltime;
            }

            public Object getArrivaltime() {
                return arrivaltime;
            }

            public void setArrivaltime(Object arrivaltime) {
                this.arrivaltime = arrivaltime;
            }

            public Object getMoney() {
                return money;
            }

            public void setMoney(Object money) {
                this.money = money;
            }

            public Object getStart_money() {
                return start_money;
            }

            public void setStart_money(Object start_money) {
                this.start_money = start_money;
            }

            public Object getMileage_money() {
                return mileage_money;
            }

            public void setMileage_money(Object mileage_money) {
                this.mileage_money = mileage_money;
            }

            public Object getReturn_money() {
                return return_money;
            }

            public void setReturn_money(Object return_money) {
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

            public Object getWait_money() {
                return wait_money;
            }

            public void setWait_money(Object wait_money) {
                this.wait_money = wait_money;
            }

            public Object getMileage() {
                return mileage;
            }

            public void setMileage(Object mileage) {
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

            public Object getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(Object paymoney) {
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

            public int getDistance() {
                return distance;
            }

            public void setDistance(int distance) {
                this.distance = distance;
            }

            public String getVIP() {
                return VIP;
            }

            public void setVIP(String VIP) {
                this.VIP = VIP;
            }
        }
    }
}
