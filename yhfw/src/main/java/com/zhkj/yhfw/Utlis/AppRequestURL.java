package com.zhkj.yhfw.Utlis;

public class AppRequestURL {
    public static final class URL {

        //长连接
        //长连接 测试
        public static final String WEBSOCKET_HOST_AND_PORT = "ws://39.98.177.238:8283";

        // 请求头测试
        public static final String HOST = "http://fztestc.xmhavefun.com";

        //长连接线上
//        public static final String WEBSOCKET_HOST_AND_PORT = "wss://hduxh.xmhavefun.com/websocket";


        // 请求头线上
//        public static final String HOST = "https://www.yihu16888.com";

        public static final  String Login=HOST+"/api/user/login";

        //获取及时信息
        public  static final String public_data=HOST+"/api/index/public_data";

        //获取个人信息
        public static final String  getUserInfo=HOST+"/api/index/getUserInfo";

        //获取订单详情
        public static  final String OrderDetail=HOST+"/api/order/orderInfo";

        //上传录音功能
        public static final String recording=HOST+"/api/recording/add";

        //获取录音功能
        public static final String recording_index=HOST+"/api/recording/index";

        //删除录音功能
        public static final String recording_delete=HOST+"/api/recording/delete";

        //开始行驶
        public static final  String trip_add_trace=HOST+"/api/order/trip_add_trace";

        //上传轨迹点
        public static final  String up_trace=HOST+"/api/order/up_trace";

        //查询yue
        public static final String index=HOST+"/api/member/index";

        //查看联系人
        public static final String emergencycontact=HOST+"/api/emergencycontact/index";

        //新增联系人
        public static final String emergencycontact_add=HOST+"/api/emergencycontact/add";

        //删除联系人
        public static final String emergencycontact_delete=HOST+"/api/emergencycontact/delete";

        //我的绩效
        public static final String team=HOST+"/api/team/index";

        //提现数据
        public static final String withdrawal_default=HOST+"/api/member/get_withdrawal_default";

        //提交提现
        public static final String withdraw=HOST+"/api/team/withdraw";

        //我的团队
        public static final String myteam=HOST+"/api/team/myteam";

        //提现明细
        public static final String withdrawlog=HOST+"/api/team/withdrawlog";

        //获取二维码
        public static final String getQR=HOST+"/api/scene/getQR";

        //佣金明细
        public static final String commission=HOST+"/api/team/commission_log";

        //发送短信
        public static final String send_sms=HOST+"/api/emergencycontact/send_sms";

    }
}
