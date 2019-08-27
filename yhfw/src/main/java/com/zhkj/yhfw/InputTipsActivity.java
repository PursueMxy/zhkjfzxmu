package com.zhkj.yhfw;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.zhkj.yhfw.Utlis.Config;
import com.zhkj.yhfw.Utlis.PopupU;

import java.util.List;

public class InputTipsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        Inputtips.InputtipsListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView mInputListView;
    private List<Tip> mCurrentTipList;
    private InputTipsAdapter mIntipAdapter;

    public static String DEFAULT_CITY;
    public static final int RESULT_CODE_INPUTTIPS = 101;
    public static final int REQUEST_SUC = 1000;
    private Context mContext;
    private int INOUT_TIPS_CODE=6699;
    private TextView tv_cityName;
    private int mType;
    //省
    private String selectedProvince;
    //市
    private String selectedCity;
    //区
    private String selectedArea;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_input_tips);
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        DEFAULT_CITY = intent.getStringExtra("city");
        type = intent.getStringExtra("type");
        initSearchView();
        mContext = getApplicationContext();
        mInputListView = findViewById(R.id.inputtip_list);
        mInputListView.setOnItemClickListener(this);
        tv_cityName = findViewById(R.id.input_tips_tv_cityName);
        tv_cityName.setOnClickListener(this);
        tv_cityName.setText(DEFAULT_CITY);
    }

    private void initSearchView() {
        SearchView searchView = findViewById(R.id.keyWord);
        searchView.setOnQueryTextListener(this);
        //设置SearchView默认为展开显示
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(true);
        searchView.setSubmitButtonEnabled(false);
        if (type.equals("0")){
            searchView.setQueryHint("请输入上车地点");
        }else if (type.equals("1")) {
            searchView.setQueryHint("请输入下车地点");
        }
    }

    /**
     * 输入提示回调
     */
    @Override
    public void onGetInputtips(List<Tip> tipList, int rCode) {
        // 正确返回
        if (rCode == REQUEST_SUC) {
            mCurrentTipList = tipList;
            mIntipAdapter = new InputTipsAdapter(getApplicationContext(), mCurrentTipList);
            mInputListView.setAdapter(mIntipAdapter);
            mIntipAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "错误码 :" + rCode, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mCurrentTipList != null) {
            Tip tip = (Tip) adapterView.getItemAtPosition(i);
            String address = tip.getDistrict()+tip.getName();
            LatLonPoint point = tip.getPoint();
            double latitude = point.getLatitude();
            double longitude = point.getLongitude();
            Log.e("address",address);
            Intent intent = new Intent();
            intent.putExtra("tip", address);
            intent.putExtra("type",type);
            intent.putExtra("latitude",""+latitude);
            intent.putExtra("longitude",""+longitude);
            setResult(INOUT_TIPS_CODE,intent);
            this.finish();
        }
    }

    /**
     * 按下确认键触发，本例为键盘回车或搜索键
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * 输入字符变化时触发
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if (!TextUtils.isEmpty(newText)) {
            InputtipsQuery inputquery = new InputtipsQuery(newText, DEFAULT_CITY);
            inputquery.setCityLimit(true);//将获取到的结果进行城市限制筛选
            Inputtips inputTips = new Inputtips(InputTipsActivity.this, inputquery);
            inputTips.setInputtipsListener(this);
            inputTips.requestInputtipsAsyn();
        } else {
            // 如果输入为空  则清除 listView 数据
            if (mIntipAdapter != null && mCurrentTipList != null) {
                mCurrentTipList.clear();
                mIntipAdapter.notifyDataSetChanged();
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.input_tips_tv_cityName:
                PopupU.showRegionView(InputTipsActivity.this, mType, selectedProvince, selectedCity, selectedArea, new PopupU.OnRegionListener() {
                    @Override
                    public void onRegionListener(String province, String city, String area) {
                        // 选择完回调结果赋值给当前
                        selectedProvince = province;
                        selectedCity = city;
                        selectedArea = area;
                        tv_cityName.setText(city);
                        DEFAULT_CITY=city;
                    }
                });
                break;
            default:
                break;
        }
    }

    private void checkType() {
        if (TextUtils.isEmpty(selectedProvince) && TextUtils.isEmpty(selectedCity) && TextUtils.isEmpty(selectedArea)) {
            mType = Config.TYPE_ADD;
        } else {
            mType = Config.TYPE_EDIT;
        }
    }
}
