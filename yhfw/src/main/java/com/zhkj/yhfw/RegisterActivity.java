package com.zhkj.yhfw;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.winfo.photoselector.PhotoSelector;
import com.zhkj.yhfw.customview.CircleImageView;

import java.io.File;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DUE_CODE = 1000;
    private static final int REACTION_CODE = 1001;
    private static final int DRIVER_CODE = 1002;
    private static final int CROP_CODE = 1003;
    private ImageView img_card_due;
    private ImageView img_card_reaction;
    private ImageView img_card_driver;
    private CircleImageView img_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitUI();
    }

    private void InitUI() {
        img_card_due = findViewById(R.id.Register_add_card_due);
        img_card_due.setOnClickListener(this);
        img_card_reaction = findViewById(R.id.Register_add_card_reaction);
        img_card_reaction.setOnClickListener(this);
        img_card_driver = findViewById(R.id.Register_add_card_driver);
        img_card_driver.setOnClickListener(this);
        img_head = findViewById(R.id.register_img_head);
        img_head.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register_add_card_due:
                //单选
                PhotoSelector.builder()
                        .setSingle(true)
                        .start(this,DUE_CODE);
                break;
            case R.id.Register_add_card_reaction:
                //单选
                PhotoSelector.builder()
                        .setSingle(true)
                        .start(this,REACTION_CODE);
                break;
            case R.id.Register_add_card_driver:
                //单选
                PhotoSelector.builder()
                        .setSingle(true)
                        .start(this,DRIVER_CODE);
                break;
            case R.id.register_img_head:
                PhotoSelector.builder()
                        .setSingle(true)//单选，裁剪都是单选
                        .setCrop(true)//是否裁剪
                        .setCropMode(PhotoSelector.CROP_CIRCLE)//设置裁剪模式 矩形还是圆形
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setToolBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setBottomBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent))
                        .start(this, CROP_CODE);
                break;
                default:
                    break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==-1){
            switch (requestCode){
                case DUE_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    ArrayList<String> imageArrayList = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT);
                    Glide.with(this).load(new File(imageArrayList.get(0))).into(img_card_due);
                    break;
                case REACTION_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    ArrayList<String> imageArrayList1 = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT);
                    Glide.with(this).load(new File(imageArrayList1.get(0))).into(img_card_reaction);
                    break;
                case DRIVER_CODE:
                    //获取到裁剪后的图片的Uri进行处理
                    ArrayList<String> imageArrayList2 = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT);
                    Glide.with(this).load(new File(imageArrayList2.get(0))).into(img_card_driver);
                    break;
                case CROP_CODE:
                    Uri resultUri = PhotoSelector.getCropImageUri(data);
                    Glide.with(this).load(resultUri).into(img_head);
                    break;
                default:
                    break;
            }
        }
    }
}
