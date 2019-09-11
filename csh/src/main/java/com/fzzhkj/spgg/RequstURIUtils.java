package com.fzzhkj.spgg;


public class RequstURIUtils {
    public static class URI{
        public static   String HOST="http://wx.cshshop.cn";

        //获取播放列表
        public static String lists=HOST+"/Api/Led/lists";

        //检查是否升级
        public static String versions=HOST+"/Api/Led/version";

        //二维码广告
        public static String AdGoodsQrcode=HOST+"/Api/led/AdGoodsQrcode";

        //二维码广告
        public static String Screen=HOST+"/Api/led/lists2";

        //激活码认证
        public static String linkusn=HOST+"/Api/Led/linkusn";

        //判断是否开启直播
        public static String livenNow=HOST+"/Api/led/Livenow";

        //获取直播地址
        public static String livrURL=HOST+"/Api/led/LiveUrl";

        //直播获取广告
        public static String GetDetailBymchid=HOST+"/Api/Led/GetDetailBymchid";

        //关闭直播状态
        public static String Liveflag=HOST+"/Api/led/Liveflag/status/0";

        //弹幕接口
        public static String Subtitle=HOST+"/Api/led/Subtitle";
    }
}
