package com.zhkj.yhfw;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhkj.yhfw.Utlis.BottomDialog;
import com.zhkj.yhfw.Utlis.StringFormatUtil;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.Utlis.ToastUtils;
import com.zhkj.yhfw.customview.MyWheelView;
import com.zhkj.yhfw.customview.WheelView;
import com.zhkj.yhfw.customview.wheelview.JudgeDate;
import com.zhkj.yhfw.customview.wheelview.ScreenInfo;
import com.zhkj.yhfw.customview.wheelview.WheelMain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class PacketTimeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_startTime;
    private Context mContext;
    private View timepickerview;
    private WheelMain wheelMain1;
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    private String address;
    private String meal_id;
    private String meal_name;
    private String positionE;
    private String positionN;
    private static final String[] PLANETS = new String[]{"市内服务", "市外服务"};
    private String service_typeName="市外服务";
    private TextView tv_service_type;
    private BottomDialog bottomDialog;
    private TextView tv_name;
    private TextView tv_startaddress;
    private String price;
    private int PACKET_TIMES_CODE = 9989;
    private long estimatedeparturetime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packet_time);
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        meal_id = intent.getStringExtra("meal_id");
        meal_name = intent.getStringExtra("meal_name");
        positionE = intent.getStringExtra("positionE");
        positionN = intent.getStringExtra("positionN");
        price = intent.getStringExtra("price");
        mContext = getApplicationContext();
        Log.e("service_typeS",positionE+positionN);
        InitUI();
    }

    private void InitUI() {
        tv_name = findViewById(R.id.packet_time_tv_name);
        StringFormatUtil spanStr = new StringFormatUtil(mContext, "包时代驾"+meal_name+"元起", price+"元", R.color.red).fillColor();
        tv_name.setText(spanStr.getResult());
        tv_startaddress = findViewById(R.id.packet_time_tv_startaddress);
        tv_startaddress.setText(address);
        tv_startTime = findViewById(R.id.packet_time_start);
        tv_startTime.setOnClickListener(this);
        tv_service_type = findViewById(R.id.packet_time_tv_service_type);
        tv_service_type.setOnClickListener(this);
        findViewById(R.id.packettime_btn_subscribe_order).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      switch (v.getId()){
          case R.id.packet_time_start:
              String startTime = tv_startTime.getText().toString();
              timepickerview = LayoutInflater.from(mContext).inflate(R.layout.timepicker, null);
              Calendar calendar1 = Calendar.getInstance();
              ScreenInfo screenInfo1 = new ScreenInfo(PacketTimeActivity.this);
              wheelMain1 = new WheelMain(timepickerview);
              wheelMain1.screenheight = screenInfo1.getHeight();
              if (JudgeDate.isDate(startTime, "yyyy.MM.dd HH:mm")) {
                  try {
                      calendar1.setTime(dateFormat.parse(startTime));
                  } catch (ParseException e) {

                      e.printStackTrace();
                  }
              }
              int year1 = calendar1.get(Calendar.YEAR);
              int month1 = calendar1.get(Calendar.MONTH);
              int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
              int hour= calendar1.get(Calendar.HOUR_OF_DAY);
              int minute = calendar1.get(Calendar.MINUTE);
              wheelMain1.initDateTimePicker(year1, month1, day1,hour,minute);
              AlertDialog.Builder dialog = new AlertDialog.Builder(PacketTimeActivity.this)
                      .setTitle("请选择出发时间")
                      .setView(timepickerview)
                      .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {

                          }
                      })
                      .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialogInterface, int i) {
                              try {
                                  boolean b = TimeUtils.dateGreater(wheelMain1.getTime());
                                  if (b){
                                      tv_startTime.setText(wheelMain1.getTime());
                                      estimatedeparturetime = TimeUtils.dateToStamp1(wheelMain1.getTime());
                                  }else {
                                      ToastUtils.showToast(mContext,"不能小于当前时间");
                                  }
                              } catch (ParseException e) {
                                  e.printStackTrace();
                              }

                          }
                      });
              dialog.show();
              break;
          case R.id.packet_time_tv_service_type:
              View outerView1 = LayoutInflater.from(this).inflate(R.layout.dialog_service_type, null);
              MyWheelView wva = outerView1.findViewById(R.id.service_type_wheel_view);
              wva.setItems(Arrays.asList(PLANETS),1);//init selected position is 1 初始选中位置为1
              wva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(int selectedIndex, String item) {
                      service_typeName=item;
                  }

              });
              outerView1.findViewById(R.id.service_type_back).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      bottomDialog.dismiss();
                  }
              });
              outerView1.findViewById(R.id.service_type_confirm).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                   tv_service_type.setText(service_typeName);
                      bottomDialog.dismiss();
                  }
              });
              bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyles);
              //将布局设置给Dialog
              bottomDialog.setContentView(outerView1);
              bottomDialog.show();//显示对话框
              break;
          case R.id.packettime_btn_subscribe_order:
              ToastUtils.showToast(mContext,"跳转");
              Intent intent1 = new Intent(this,BaoTimesActivity.class);
              intent1.putExtra("codes","1");
              intent1.putExtra("estimatedeparturetime",estimatedeparturetime+"");
              intent1.putExtra("service_type",service_typeName);
              intent1.putExtra("meal_id",meal_id+"");
              intent1.putExtra("position",address);
              intent1.putExtra("positionE",positionE);
              intent1.putExtra("positionN",positionN);
              setResult(PACKET_TIMES_CODE,intent1);
              finish();
              break;
              default:
                  break;
      }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent1 = new Intent(this,BaoTimesActivity.class);
            intent1.putExtra("codes","0");
            setResult(PACKET_TIMES_CODE,intent1);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
