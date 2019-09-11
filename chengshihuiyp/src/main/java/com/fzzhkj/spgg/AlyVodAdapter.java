package com.fzzhkj.spgg;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class AlyVodAdapter extends RecyclerView.Adapter<AlyVodAdapter.ViewHolder> {

    private Context mContext;
    private List<subtitle.DataBean> list =new ArrayList<>();

    public AlyVodAdapter(Context context, List<subtitle.DataBean> list){
          this.mContext=context;
          this.list=list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alyvod, parent,
                false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.tv_typename.setText(list.get(position).getTitle());
        holder.tv_alyvod_name.setText(list.get(position).getNickname()+"：");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView tv_typename;
        TextView tv_alyvod_name;

        public ViewHolder(View itemView) {
        super(itemView);
            tv_typename=itemView.findViewById(R.id.list_alyvod_title);
            tv_alyvod_name = itemView.findViewById(R.id.list_alyvod_name);
        }
}
}
