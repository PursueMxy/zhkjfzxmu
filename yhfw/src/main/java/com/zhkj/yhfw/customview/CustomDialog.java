package com.zhkj.yhfw.customview;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.necer.ndialog.NDialog;
import com.zhkj.yhfw.R;

public class CustomDialog extends NDialog {
    public CustomDialog(Context context) {
        super(context);
    }
    @Override
    protected void setDialogDetails(Context context, AlertDialog alertDialog) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_order_info, null);
        alertDialog.setContentView(inflate);

        //可在这里设置 NDialog 的属性

        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setDimAmount(0.0F);

    }
}
