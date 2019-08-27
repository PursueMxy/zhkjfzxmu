package com.zhkj.yhfw.Bean;

import java.util.List;

public class BaoTimesBean {

    /**
     * code : 200
     * msg : ok
     * time : 1565343785
     * data : {"meal":[{"id":1,"name":"4小时包时套餐","start_time":"08:00:00","end_time":"20:00:00","duration":4,"price":"168.00","over_pricce":"10.00","max_mileage":"300","free_return_mileage":20,"return_price":"10.00","free_over_time":30,"createtime":1560735757,"updatetime":1564337409,"status":"1","duration_text":"4小时"},{"id":2,"name":"1天包时套餐","start_time":"18:34:50","end_time":"18:34:50","duration":24,"price":"358.00","over_pricce":"10.00","max_mileage":"600","free_return_mileage":20,"return_price":"10.00","free_over_time":30,"createtime":1561286217,"updatetime":1561286943,"status":"1","duration_text":"1天"}]}
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
        private List<MealBean> meal;

        public List<MealBean> getMeal() {
            return meal;
        }

        public void setMeal(List<MealBean> meal) {
            this.meal = meal;
        }

        public static class MealBean {
            /**
             * id : 1
             * name : 4小时包时套餐
             * start_time : 08:00:00
             * end_time : 20:00:00
             * duration : 4
             * price : 168.00
             * over_pricce : 10.00
             * max_mileage : 300
             * free_return_mileage : 20
             * return_price : 10.00
             * free_over_time : 30
             * createtime : 1560735757
             * updatetime : 1564337409
             * status : 1
             * duration_text : 4小时
             */

            private int id;
            private String name;
            private String start_time;
            private String end_time;
            private int duration;
            private String price;
            private String over_pricce;
            private String max_mileage;
            private int free_return_mileage;
            private String return_price;
            private int free_over_time;
            private int createtime;
            private int updatetime;
            private String status;
            private String duration_text;

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

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getOver_pricce() {
                return over_pricce;
            }

            public void setOver_pricce(String over_pricce) {
                this.over_pricce = over_pricce;
            }

            public String getMax_mileage() {
                return max_mileage;
            }

            public void setMax_mileage(String max_mileage) {
                this.max_mileage = max_mileage;
            }

            public int getFree_return_mileage() {
                return free_return_mileage;
            }

            public void setFree_return_mileage(int free_return_mileage) {
                this.free_return_mileage = free_return_mileage;
            }

            public String getReturn_price() {
                return return_price;
            }

            public void setReturn_price(String return_price) {
                this.return_price = return_price;
            }

            public int getFree_over_time() {
                return free_over_time;
            }

            public void setFree_over_time(int free_over_time) {
                this.free_over_time = free_over_time;
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

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDuration_text() {
                return duration_text;
            }

            public void setDuration_text(String duration_text) {
                this.duration_text = duration_text;
            }
        }
    }
}
