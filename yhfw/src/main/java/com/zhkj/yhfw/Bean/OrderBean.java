package com.zhkj.yhfw.Bean;

import org.litepal.crud.LitePalSupport;

public class OrderBean extends LitePalSupport {

    private String OrderName;

    private String StartDt;

    private String StopDt;

    public String getOrderName() {
        return OrderName;
    }

    public void setOrderName(String orderName) {
        OrderName = orderName;
    }

    public String getStartDt() {
        return StartDt;
    }

    public void setStartDt(String startDt) {
        StartDt = startDt;
    }

    public String getStopDt() {
        return StopDt;
    }

    public void setStopDt(String stopDt) {
        StopDt = stopDt;
    }
}
