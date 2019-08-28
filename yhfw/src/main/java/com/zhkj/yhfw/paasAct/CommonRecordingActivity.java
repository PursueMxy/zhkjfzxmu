package com.zhkj.yhfw.paasAct;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhkj.yhfw.Bean.PublicResultBean;
import com.zhkj.yhfw.Bean.RecordingBean;
import com.zhkj.yhfw.MyApplication;
import com.zhkj.yhfw.PaaSActivity;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.DownloadUtil;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhkj.yhfw.adapter.SearchResultAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class CommonRecordingActivity extends AppCompatActivity {
    private int COMMON_FIVE=2005;
    private String token;
    private Context mContext;
    private MediaPlayer mPlayer;
    private ListView commonRecord_list;
    private MyAdapter myAdapter;
    private List<RecordingBean.DataBean> data=new ArrayList<>();
    private String sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_recording);
        sounds = new File(Environment.getExternalStorageDirectory(), "sounds").getAbsolutePath();
        SharedPreferences sp = this.getSharedPreferences("login", Context.MODE_PRIVATE);
        token = sp.getString("token", "");
        mContext = getApplicationContext();
        findViewById(R.id.CommonRecording_img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommonRecordingActivity.this, PaaSActivity.class);
                setResult(COMMON_FIVE,intent);
                finish();
            }
        });
        commonRecord_list = findViewById(R.id.commonRecord_list);
        myAdapter = new MyAdapter();
        commonRecord_list.setAdapter(myAdapter);
        InitData();
    }

    private void InitData() {
        OkGo.<String>get(AppRequestURL.URL.recording_index)
                .params("type","1")
                .params("token", token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("录音列表",response.body());
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        RecordingBean recordingBean = gson.fromJson(response.body(), RecordingBean.class);
                        if (recordingBean!=null){
                            if (recordingBean.getCode()==200){
                                data = recordingBean.getData();
                                myAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent intent = new Intent(this, PaaSActivity.class);
            setResult(COMMON_FIVE,intent);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void PyayMedia(String AudioPath){
        Log.e("AudioPath",AudioPath+"和"+sounds);

        DownloadUtil.getInstance().download(AudioPath,sounds, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {
                mPlayer = new MediaPlayer();
                try {
                    mPlayer.setDataSource(path);
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDownloading(int progress) {
            }

            @Override
            public void onDownloadFailed() {
            }
        });
    }

    public class MyAdapter extends BaseAdapter{


        private ImageView img_reading;
        private ImageView img_delete;
        private TextView tv_dt;

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = getLayoutInflater().inflate(R.layout.list_common_recoed, null);
            } else{
                view = convertView;
            }
            img_reading = view.findViewById(R.id.list_recoed_img_reading);
            img_delete = view.findViewById(R.id.list_recoed_reading_img_delete);
            tv_dt = view.findViewById(R.id.list_recoed_tv_dt);
            tv_dt.setText(TimeUtils.getDateToString(data.get(position).getCreatetime())+"");
            img_reading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PyayMedia(AppRequestURL.URL.HOST+data.get(position).getPath());
                }
            });
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OkGo.<String>get(AppRequestURL.URL.recording_delete)
                            .params("type","1")
                            .params("token",token)
                            .params("recording_id",data.get(position).getId())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    GsonBuilder gsonBuilder = new GsonBuilder();
                                    Gson gson = gsonBuilder.create();
                                    PublicResultBean publicResultBean = gson.fromJson(response.body(), PublicResultBean.class);
                                    if (publicResultBean!=null){
                                        if (publicResultBean.getCode()==201){
                                            InitData();
                                        }
                                    }
                                }
                            });
                }
            });
            return view;
        }
    }
}
