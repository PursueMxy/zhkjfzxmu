package com.fzzhkj.spgg;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 微软中国 on 2018/10/27.
 */

public class ToastUtils {
    private static Toast toast;
    public  static void showToast1(Context context, String text) {
        if (toast != null){
            toast.cancel();
        }
        toast = new Toast(context);
        View view = LayoutInflater.from(context).inflate(R.layout.mytoast, null);
        TextView tvText = (TextView) view.findViewById(R.id.textView1);
        tvText.setText(text);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 60);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
