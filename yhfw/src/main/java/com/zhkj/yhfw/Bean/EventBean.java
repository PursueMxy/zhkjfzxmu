package com.zhkj.yhfw.Bean;

public class EventBean {

    /**
     * event : arrive
     * code : 200
     * msg : 到达目的地
     * data : 168
     */

    private String event;
    private int code;
    private String msg;
    private int data;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
