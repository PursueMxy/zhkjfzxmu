package com.zhkj.yhfw.Utlis;

public class AppRequestURL {
    public static final class URL {
        //请求头
        public static final String HOST = "http://fztestc.xmhavefun.com";

        public static final  String Login=HOST+"/api/user/login";

        //获取及时信息
        public  static final String public_data=HOST+"/api/index/public_data";

        //获取个人信息
        public static final String  getUserInfo=HOST+"/api/index/getUserInfo";

        //获取订单详情
        public static  final String OrderDetail=HOST+"/api/order/orderInfo";

        //上传录音功能
        public static final String recording=HOST+"/api/recording/add";

    }
}
