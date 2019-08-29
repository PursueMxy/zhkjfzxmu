package com.zhkj.yhfw.Bean;

import java.util.List;

public class MyTeamBean {


    /**
     * code : 200
     * msg : ok
     * time : 1567072164
     * data : {"team_member":[{"id":12,"nickname":"夏利华","avatar":"/uploads/tx/20190805175344_.jpeg","createtime":1564999088,"fprofit":"12.00","team_member_count":0},{"id":13,"nickname":"杨宏彬","avatar":"/uploads/tx/20190805183803_.jpeg","createtime":1565001489,"fprofit":"12.00","team_member_count":0},{"id":14,"nickname":"11","avatar":"/uploads/tx/20190812185752_.jpeg","createtime":1565607492,"fprofit":"12.00","team_member_count":0}]}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<TeamMemberBean> team_member;

        public List<TeamMemberBean> getTeam_member() {
            return team_member;
        }

        public void setTeam_member(List<TeamMemberBean> team_member) {
            this.team_member = team_member;
        }

        public static class TeamMemberBean {
            /**
             * id : 12
             * nickname : 夏利华
             * avatar : /uploads/tx/20190805175344_.jpeg
             * createtime : 1564999088
             * fprofit : 12.00
             * team_member_count : 0
             */

            private int id;
            private String nickname;
            private String avatar;
            private int createtime;
            private String fprofit;
            private int team_member_count;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getCreatetime() {
                return createtime;
            }

            public void setCreatetime(int createtime) {
                this.createtime = createtime;
            }

            public String getFprofit() {
                return fprofit;
            }

            public void setFprofit(String fprofit) {
                this.fprofit = fprofit;
            }

            public int getTeam_member_count() {
                return team_member_count;
            }

            public void setTeam_member_count(int team_member_count) {
                this.team_member_count = team_member_count;
            }
        }
    }
}
