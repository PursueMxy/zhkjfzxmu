package com.zhkj.yhfw.Utlis;

import org.litepal.crud.LitePalSupport;

/**
 * @author Administrator
 */
public class LiteDataBean extends LitePalSupport {

    private String dbid;
    private String title;
    private String start_dt;
    private String lat;
    private String lng;


    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart_dt() {
        return start_dt;
    }

    public void setStart_dt(String start_dt) {
        this.start_dt = start_dt;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

}
