package com.fzzhkj.spgg;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DifferentDislay extends Presentation {

    private ImageView img_qrcode;
    private Context mContext;

    public DifferentDislay(Context outerContext, Display display) {
        super(outerContext, display);
        this.mContext=outerContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_screen);
        img_qrcode = findViewById(R.id.second_screen_img_qrcode);
    }
    public ImageView getImg_qrcode(String qrcoUrl){
        if (img_qrcode!=null) {
            Glide.with(mContext).load(qrcoUrl).into(img_qrcode);
        }else {
            img_qrcode = findViewById(R.id.second_screen_img_qrcode);
            Glide.with(mContext).load(qrcoUrl).into(img_qrcode);
        }
        return img_qrcode;
    }
}
