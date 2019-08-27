package com.zhkj.yhfw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhkj.yhfw.R;

import java.util.ArrayList;
import java.util.Map;

public class GridAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private Context mContext;
    ArrayList<Map<String, Object>> dataList;

    public  GridAdapter (Context context, ArrayList<Map<String, Object>> dataList){
        this.dataList=dataList;
        this.mContext=context;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.gridview_item, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.img=convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String text = (String) dataList.get(position).get("text");
        holder.text.setText(text);
        holder.img.setImageResource((int)dataList.get(position).get("img"));
        return convertView;
    }
    class ViewHolder {
        TextView text;
        ImageView img;
    }
}
