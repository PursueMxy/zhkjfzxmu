package com.zhkj.yhfw.Bean;

import java.util.List;

public class PublicDataBean {

    /**
     * code : 200
     * msg : 请求成功
     * time : 1565085349
     * data : {"driver_index_img":"/uploads/20190629/841b1c6c246f58e3ec2d464458f9d35d.jpg","user_index_img":null,"recharge_doc":"<p>1、充值后可通过提现<\/p><p>2、充值成功后若金额无变化，请刷新页面。<\/p>","urgent_contact_doc":"<p>1、可设置多个紧急联系人<\/p><p>2、可对紧急联系人发送短信<\/p>","tape_doc":"<p>1、可对代驾全程进行录音管理<\/p><p>2、可对录音进行管理<\/p>","apply_QRcode_doc":"二维码可用于团队发展，保持长期收益。","withdraw_doc":"<p>1、提现申请提交后，系统直接扣钱<\/p><p>2、需要等待后台审核，不可提现的金额系统将返原账户。<\/p><p>3、若有疑问请联系客服：0592-5711628<\/p>","applyVIP_doc":"<p>1.申请VIP需要后台进行审核<br><\/p><p>2.需要填写完整信息，以便更好通过审核<\/p><p>3.成为VIP后将享有平台开放的团队与钱包功能。<\/p><p><br><\/p>","debug":0,"free_waiting_time":"10","waiting_price":"1","invoice_doc":"请认真填写信息，以便快速审核。","company_address":"厦门市","company_mobile":"0592-5711628","company_name":"一乎服务","banner":[{"id":3,"bannerimage":"/uploads/20190725/a13c31a1c4ee84bb2654ed2edd7bb7b7.jpg","link":"","showdata":"0","status":"1","name":"","weigh":3,"createtime":1561814730,"updatetime":1564063477}]}
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
         * driver_index_img : /uploads/20190629/841b1c6c246f58e3ec2d464458f9d35d.jpg
         * user_index_img : null
         * recharge_doc : <p>1、充值后可通过提现</p><p>2、充值成功后若金额无变化，请刷新页面。</p>
         * urgent_contact_doc : <p>1、可设置多个紧急联系人</p><p>2、可对紧急联系人发送短信</p>
         * tape_doc : <p>1、可对代驾全程进行录音管理</p><p>2、可对录音进行管理</p>
         * apply_QRcode_doc : 二维码可用于团队发展，保持长期收益。
         * withdraw_doc : <p>1、提现申请提交后，系统直接扣钱</p><p>2、需要等待后台审核，不可提现的金额系统将返原账户。</p><p>3、若有疑问请联系客服：0592-5711628</p>
         * applyVIP_doc : <p>1.申请VIP需要后台进行审核<br></p><p>2.需要填写完整信息，以便更好通过审核</p><p>3.成为VIP后将享有平台开放的团队与钱包功能。</p><p><br></p>
         * debug : 0
         * free_waiting_time : 10
         * waiting_price : 1
         * invoice_doc : 请认真填写信息，以便快速审核。
         * company_address : 厦门市
         * company_mobile : 0592-5711628
         * company_name : 一乎服务
         * banner : [{"id":3,"bannerimage":"/uploads/20190725/a13c31a1c4ee84bb2654ed2edd7bb7b7.jpg","link":"","showdata":"0","status":"1","name":"","weigh":3,"createtime":1561814730,"updatetime":1564063477}]
         */

        private String driver_index_img;
        private Object user_index_img;
        private String recharge_doc;
        private String urgent_contact_doc;
        private String tape_doc;
        private String apply_QRcode_doc;
        private String withdraw_doc;
        private String applyVIP_doc;
        private int debug;
        private String free_waiting_time;
        private String waiting_price;
        private String invoice_doc;
        private String company_address;
        private String company_mobile;
        private String company_name;
        private List<BannerBean> banner;

        public String getDriver_index_img() {
            return driver_index_img;
        }

        public void setDriver_index_img(String driver_index_img) {
            this.driver_index_img = driver_index_img;
        }

        public Object getUser_index_img() {
            return user_index_img;
        }

        public void setUser_index_img(Object user_index_img) {
            this.user_index_img = user_index_img;
        }

        public String getRecharge_doc() {
            return recharge_doc;
        }

        public void setRecharge_doc(String recharge_doc) {
            this.recharge_doc = recharge_doc;
        }

        public String getUrgent_contact_doc() {
            return urgent_contact_doc;
        }

        public void setUrgent_contact_doc(String urgent_contact_doc) {
            this.urgent_contact_doc = urgent_contact_doc;
        }

        public String getTape_doc() {
            return tape_doc;
        }

        public void setTape_doc(String tape_doc) {
            this.tape_doc = tape_doc;
        }

        public String getApply_QRcode_doc() {
            return apply_QRcode_doc;
        }

        public void setApply_QRcode_doc(String apply_QRcode_doc) {
            this.apply_QRcode_doc = apply_QRcode_doc;
        }

        public String getWithdraw_doc() {
            return withdraw_doc;
        }

        public void setWithdraw_doc(String withdraw_doc) {
            this.withdraw_doc = withdraw_doc;
        }

        public String getApplyVIP_doc() {
            return applyVIP_doc;
        }

        public void setApplyVIP_doc(String applyVIP_doc) {
            this.applyVIP_doc = applyVIP_doc;
        }

        public int getDebug() {
            return debug;
        }

        public void setDebug(int debug) {
            this.debug = debug;
        }

        public String getFree_waiting_time() {
            return free_waiting_time;
        }

        public void setFree_waiting_time(String free_waiting_time) {
            this.free_waiting_time = free_waiting_time;
        }

        public String getWaiting_price() {
            return waiting_price;
        }

        public void setWaiting_price(String waiting_price) {
            this.waiting_price = waiting_price;
        }

        public String getInvoice_doc() {
            return invoice_doc;
        }

        public void setInvoice_doc(String invoice_doc) {
            this.invoice_doc = invoice_doc;
        }

        public String getCompany_address() {
            return company_address;
        }

        public void setCompany_address(String company_address) {
            this.company_address = company_address;
        }

        public String getCompany_mobile() {
            return company_mobile;
        }

        public void setCompany_mobile(String company_mobile) {
            this.company_mobile = company_mobile;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public static class BannerBean {
            /**
             * id : 3
             * bannerimage : /uploads/20190725/a13c31a1c4ee84bb2654ed2edd7bb7b7.jpg
             * link :
             * showdata : 0
             * status : 1
             * name :
             * weigh : 3
             * createtime : 1561814730
             * updatetime : 1564063477
             */

            private int id;
            private String bannerimage;
            private String link;
            private String showdata;
            private String status;
            private String name;
            private int weigh;
            private int createtime;
            private int updatetime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBannerimage() {
                return bannerimage;
            }

            public void setBannerimage(String bannerimage) {
                this.bannerimage = bannerimage;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getShowdata() {
                return showdata;
            }

            public void setShowdata(String showdata) {
                this.showdata = showdata;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getWeigh() {
                return weigh;
            }

            public void setWeigh(int weigh) {
                this.weigh = weigh;
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
        }
    }
}
