package com.zhkj.yhfw.adapter;

import android.content.Context;
import android.widget.TextView;

import com.zhkj.yhfw.Bean.CommissionBean;
import com.zhkj.yhfw.Bean.WithdrawlogBean;
import com.zhkj.yhfw.R;
import com.zhkj.yhfw.Utlis.TimeUtils;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class CommissionAdapter extends HelperRecyclerViewAdapter<CommissionBean.DataBean.ListBean> {

    public Context mContext;
    public CommissionAdapter(Context context) {
        super(context, R.layout.list_withdraw);
        mContext=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, CommissionBean.DataBean.ListBean item) {
        CommissionBean.DataBean.ListBean data = getData(position);
        TextView tv_dt = viewHolder.getView(R.id.list_withdraw_tv_dt);
        TextView tv_money = viewHolder.getView(R.id.list_withdraw_tv_money);
        TextView tv_status = viewHolder.getView(R.id.list_withdraw_tv_status);
        tv_money.setText("+"+data.getUp_driver_income());
        tv_dt.setText(TimeUtils.getDateToString(data.getCreatetime()));
        tv_status.setText(data.getNickname());
    }
}
