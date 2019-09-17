package com.fzzhkj.spgg.Bean;

import java.util.List;

public class DetailBymchidBean {

    /**
     * status : 1
     * msg : 获取成功
     * data : {"leftmoney":"http://zmy.0598qq.com/Public/left.jpg","thegoods":[{"id":"432","title":"净姐衣服漂白剂白色衣物去黄增白去染色通用漂泊粉漂白水","buynum":"91","money":"19.90","price":"39","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-08/5d4be4290fc55.jpg"},{"id":"780","title":"ViViMoschine银离子抗菌螺纹棉内裤美体弹力透气组合3条装","buynum":"0","money":"59.00","price":"90","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-12/5d510a0b28148.jpg"}],"right":"http://zmy.0598qq.com/Public/right.jpg"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * leftmoney : http://zmy.0598qq.com/Public/left.jpg
         * thegoods : [{"id":"432","title":"净姐衣服漂白剂白色衣物去黄增白去染色通用漂泊粉漂白水","buynum":"91","money":"19.90","price":"39","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-08/5d4be4290fc55.jpg"},{"id":"780","title":"ViViMoschine银离子抗菌螺纹棉内裤美体弹力透气组合3条装","buynum":"0","money":"59.00","price":"90","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-12/5d510a0b28148.jpg"}]
         * right : http://zmy.0598qq.com/Public/right.jpg
         */

        private String leftmoney;
        private String right;
        private List<ThegoodsBean> thegoods;

        public String getLeftmoney() {
            return leftmoney;
        }

        public void setLeftmoney(String leftmoney) {
            this.leftmoney = leftmoney;
        }

        public String getRight() {
            return right;
        }

        public void setRight(String right) {
            this.right = right;
        }

        public List<ThegoodsBean> getThegoods() {
            return thegoods;
        }

        public void setThegoods(List<ThegoodsBean> thegoods) {
            this.thegoods = thegoods;
        }

        public static class ThegoodsBean {
            /**
             * id : 432
             * title : 净姐衣服漂白剂白色衣物去黄增白去染色通用漂泊粉漂白水
             * buynum : 91
             * money : 19.90
             * price : 39
             * topimg : http://csh.0598qq.com/Uploads/Picture/2019-08-08/5d4be4290fc55.jpg
             */

            private String id;
            private String title;
            private String buynum;
            private String money;
            private String price;
            private String topimg;

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

            public String getBuynum() {
                return buynum;
            }

            public void setBuynum(String buynum) {
                this.buynum = buynum;
            }

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getTopimg() {
                return topimg;
            }

            public void setTopimg(String topimg) {
                this.topimg = topimg;
            }
        }
    }
}
