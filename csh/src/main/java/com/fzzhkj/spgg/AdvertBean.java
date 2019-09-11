package com.fzzhkj.spgg;

import java.util.List;

public class AdvertBean {

    /**
     * status : 1
     * msg : 获取成功
     * data : {"leftmoney":"http://csh.0598qq.com/Public/left2.jpg","thegoods":[{"id":"412","title":"抖音同款防护喷雾脖子面部隔离紫外线清爽","buynum":"188","money":"29.90","price":"79","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e619aacfb2.jpg"},{"id":"420","title":"净姐草本净菌洗衣液2kg瓶薰衣草香持久留香家庭用","buynum":"84","money":"35.00","price":"69","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e76d89ad3e.jpg"}],"right":"http://csh.0598qq.com/Public/right.jpg"}
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
         * leftmoney : http://csh.0598qq.com/Public/left2.jpg
         * thegoods : [{"id":"412","title":"抖音同款防护喷雾脖子面部隔离紫外线清爽","buynum":"188","money":"29.90","price":"79","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e619aacfb2.jpg"},{"id":"420","title":"净姐草本净菌洗衣液2kg瓶薰衣草香持久留香家庭用","buynum":"84","money":"35.00","price":"69","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e76d89ad3e.jpg"}]
         * right : http://csh.0598qq.com/Public/right.jpg
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
             * id : 412
             * title : 抖音同款防护喷雾脖子面部隔离紫外线清爽
             * buynum : 188
             * money : 29.90
             * price : 79
             * topimg : http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e619aacfb2.jpg
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
