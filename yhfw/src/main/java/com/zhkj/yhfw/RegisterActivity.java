package com.zhkj.yhfw;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winfo.photoselector.PhotoSelector;
import com.zhkj.yhfw.Utlis.BottomDialog;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.Utlis.ToastUtils;
import com.zhkj.yhfw.customview.CircleImageView;
import com.zhkj.yhfw.customview.wheelview.JudgeDate;
import com.zhkj.yhfw.customview.wheelview.ScreenInfo;
import com.zhkj.yhfw.customview.wheelview.WheelMain;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DUE_CODE = 1000;
    private static final int REACTION_CODE = 1001;
    private static final int DRIVER_CODE = 1002;
    private static final int CROP_CODE = 1003;
    private ImageView img_card_due;
    private ImageView img_card_reaction;
    private ImageView img_card_driver;
    private CircleImageView img_head;
    private EditText edt_name;
    private EditText edt_mobile;
    private EditText edt_verification_code;
    private EditText edt_resume_password;
    private EditText edt_password;
    private TextView tv_card_dt;
    private String mobile;
    private String name;
    private String password;
    private String resume_password;
    private String verification_code;
    private WheelMain wheelMain;
    private String card_dt="";
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private Context mContext;
    private View.OnClickListener 不能小于当前时间;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = getApplicationContext();
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
        edt_name = findViewById(R.id.register_edt_name);
        edt_mobile = findViewById(R.id.register_edt_mobile);
        edt_verification_code = findViewById(R.id.register_edt_verification_code);
        edt_resume_password = findViewById(R.id.register_edt_resume_password);
        edt_password = findViewById(R.id.register_edt_password);
        tv_card_dt = findViewById(R.id.register_tv_card_dt);
        findViewById(R.id.register_btn_confirm).setOnClickListener(this);
        tv_card_dt.setOnClickListener(this);
        card_dt = tv_card_dt.getText().toString();
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
            case R.id.register_btn_confirm:
                name = edt_name.getText().toString();
                mobile = edt_mobile.getText().toString();
                password = edt_password.getText().toString();
                resume_password = edt_resume_password.getText().toString();
                verification_code = edt_verification_code.getText().toString();
                card_dt = tv_card_dt.getText().toString();
                break;
            case R.id.register_tv_card_dt:
                final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
                View inflate = LayoutInflater.from(this).inflate(R.layout.register_timepicker, null);
               inflate.findViewById(R.id.register_dialog_tv_back).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       bottomDialog.dismiss();
                   }
               });
                inflate.findViewById(R.id.register_dialog_tv_confirm).setOnClickListener( new View.OnClickListener() {

                    private long card_dt_long;

                    @Override
                    public void onClick(View v) {
                        bottomDialog.dismiss();
                        boolean b = false;
                        try {
                            b = TimeUtils.dateGreaters(wheelMain.getTime());
                            if (b) {
                                tv_card_dt.setText(wheelMain.getTime());
                                card_dt_long = TimeUtils.dateToStamp1s(wheelMain.getTime());
                            } else {
                                ToastUtils.showToast(mContext, "不能小于当前时间");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Calendar calendar1 = Calendar.getInstance();
                ScreenInfo screenInfo1 = new ScreenInfo(RegisterActivity.this);
                wheelMain = new WheelMain(inflate,false);
                wheelMain.screenheight = screenInfo1.getHeight();
                if (JudgeDate.isDate(card_dt, "yyyy.MM.dd")) {
                    try {
                        calendar1.setTime(dateFormat.parse(card_dt));
                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                }
                int year1 = calendar1.get(Calendar.YEAR);
                int month1 = calendar1.get(Calendar.MONTH);
                int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                wheelMain.initDateTimePicker(year1, month1, day1);
                //将布局设置给Dialog
                bottomDialog.setContentView(inflate);
                //获取当前Activity所在的窗体
                Window dialogWindow = bottomDialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity( Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
                dialogWindow.setAttributes(lp);

                bottomDialog.show();//显示对话框
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
