package com.fzzhkj.widget;

import android.content.Context;
import android.widget.RelativeLayout;

import com.fzzhkj.theme.ITheme;

public class AliyunVodPlayerView extends RelativeLayout implements ITheme {


    public AliyunVodPlayerView(Context context) {
        super(context);
    }

    @Override
    public void setTheme(Theme theme) {

    }

    /**
     * UI播放器支持的主题
     */
    public static enum Theme {
        /**
         * 蓝色主题
         */
        Blue,
        /**
         * 绿色主题
         */
        Green,
        /**
         * 橙色主题
         */
        Orange,
        /**
         * 红色主题
         */
        Red
    }
}
