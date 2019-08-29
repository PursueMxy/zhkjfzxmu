package com.zhkj.yhfw.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhkj.yhfw.Bean.MyTeamBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.List;

public class MyTeamAdapter extends HelperRecyclerViewAdapter<MyTeamBean.DataBean.TeamMemberBean> {

    public Context mContext;
    public MyTeamAdapter(Context context) {
        super(context, R.layout.list_myteam);
        mContext=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, MyTeamBean.DataBean.TeamMemberBean item) {
        MyTeamBean.DataBean.TeamMemberBean data = getData(position);
        TextView tv_name = viewHolder.getView(R.id.list_my_team_tv_name);
        TextView tv_inset_dt = viewHolder.getView(R.id.list_my_team_inset_dt);
        TextView tv_tg = viewHolder.getView(R.id.list_my_team_tv_tg);
        TextView tv_sy = viewHolder.getView(R.id.list_my_team_tv_sy);
        ImageView img= viewHolder.getView(R.id.list_my_team_img);
        tv_name.setText("姓名："+data.getNickname());
        tv_inset_dt.setText("注册时间："+ TimeUtils.getDayToString(data.getCreatetime()));
        tv_tg.setText("推广"+data.getTeam_member_count()+"人");
        tv_sy.setText(data.getFprofit()+"元");
        Glide.with(mContext).load(AppRequestURL.URL.HOST+data.getAvatar()).into(img);
    }
}
