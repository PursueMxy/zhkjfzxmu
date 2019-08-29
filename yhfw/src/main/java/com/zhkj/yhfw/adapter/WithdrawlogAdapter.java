package com.zhkj.yhfw.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhkj.yhfw.Bean.MyTeamBean;
import com.zhkj.yhfw.Bean.WithdrawlogBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.AppRequestURL;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class WithdrawlogAdapter extends HelperRecyclerViewAdapter<WithdrawlogBean.DataBean.ListBean> {

    public Context mContext;
    public WithdrawlogAdapter(Context context) {
        super(context, R.layout.list_withdraw);
        mContext=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, WithdrawlogBean.DataBean.ListBean item) {
        WithdrawlogBean.DataBean.ListBean data = getData(position);
        TextView tv_dt = viewHolder.getView(R.id.list_withdraw_tv_dt);
        TextView tv_money = viewHolder.getView(R.id.list_withdraw_tv_money);
        TextView tv_status = viewHolder.getView(R.id.list_withdraw_tv_status);
        tv_money.setText("¥："+data.getMoney());
        tv_dt.setText(TimeUtils.getDateToString(data.getCreatetime()));
        if (data.getStatus().equals("1")){
            tv_status.setText("待审核");
        }else if (data.getStatus().equals("2")){
            tv_status.setText("待打款");
        }else if (data.getStatus().equals("3")){
            tv_status.setText("已打款");
        }else if (data.getStatus().equals("4")){
            tv_status.setText("无效");
        }
    }
}
