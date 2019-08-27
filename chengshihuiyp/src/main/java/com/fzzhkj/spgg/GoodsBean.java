package com.fzzhkj.spgg;

import java.util.List;

public class GoodsBean {

    /**
     * status : 1
     * msg : 获取成功
     * data : [{"id":"465","title":"TATIE塔铁生物酶拖地水一盖清洁半个屋除腥去污杀菌三合一525ml","buynum":"154","money":"29.00","price":"55","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e5e367c77e.jpg"},{"id":"425","title":"净姐厨房去重油污强力清洗剂油烟机除油剂油污烟净除垢家用清洁剂","buynum":"254","money":"20.90","price":"35","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-07-06/5d204631e766f.jpg"},{"id":"415","title":"韩纪淡化黑眼圈去细纹补水保湿提拉紧致修复熬夜眼霜","buynum":"208","money":"59.00","price":"129","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-07-05/5d1f0ba272cc0.jpg"},{"id":"557","title":"高缇雅玫瑰花瓣沐浴露花香嫩肤美白沐浴露208ml","buynum":"249","money":"18.90","price":"59","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-07-22/5d3534232fc05.jpg"},{"id":"519","title":"时尚家用电风扇台地两用落地扇7叶静音摇头电扇节能省电COLOR白色","buynum":"251","money":"99.90","price":"129","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-07-18/5d2fdbd242e34.jpg"},{"id":"528","title":"颈椎按摩器肩颈电动多功能护颈仪智能理疗肩按摩仪BSX-H61-BK","buynum":"141","money":"168.00","price":"329","topimg":"http://csh.0598qq.com/Uploads/Picture/2019-07-18/5d30074c8287c.jpg"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 465
         * title : TATIE塔铁生物酶拖地水一盖清洁半个屋除腥去污杀菌三合一525ml
         * buynum : 154
         * money : 29.00
         * price : 55
         * topimg : http://csh.0598qq.com/Uploads/Picture/2019-08-10/5d4e5e367c77e.jpg
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
