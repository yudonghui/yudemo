package com.ydh.yudemo.retrofitrxjava.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by UI on 2018/9/27.
 */

public class User implements Serializable{

    /**
     * code : 0
     * msg : 成功
     * totalPage : 180
     * data : [{"id":"2762","rid":"1000797","author":"网络","title":"于氏","summary":"于氏家谱","picurl":null,"count":"1","create_time":"1538023180","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"中国","nikename":"东东","avatar":"11,0c36bc1e07ce"},{"id":"2761","rid":"1000794","author":"网络","title":"测试新闻","summary":"测试新闻","picurl":null,"count":"21","create_time":"1533694249","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"浪迹天涯","avatar":null},{"id":"2760","rid":"1000795","author":"网络","title":"1231231","summary":"312313\n312321321\n","picurl":null,"count":"7","create_time":"1533204316","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"https://res.ixungen.cn/img/9,247fe7886055;https://res.ixungen.cn/img/10,247ef73c9486","city":"上海市","nikename":"董小硕","avatar":"10,0c04fbec28b4"},{"id":"2759","rid":"1000794","author":"网络","title":"Qqqqqqqqqq","summary":"Tqetqerteqrtqertretqer\n","picurl":null,"count":"18","create_time":"1533201707","status":"1","reply_number":"2","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"浪迹天涯","avatar":null},{"id":"2758","rid":"1000794","author":"网络","title":"我是测试数据","summary":"Qweqgqrgeqrgerqgqregreqgergerqgerqergreqgeqgqe\n","picurl":null,"count":"31","create_time":"1533108882","status":"1","reply_number":"1","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"浪迹天涯","avatar":null},{"id":"2757","rid":"1000801","author":"网络","title":" ","summary":null,"picurl":null,"count":"0","create_time":"1511594419","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"惋","avatar":"9,0e46afca7c18"},{"id":"2756","rid":"1000801","author":"网络","title":" ","summary":null,"picurl":null,"count":"1","create_time":"1511503997","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"惋","avatar":"9,0e46afca7c18"},{"id":"2755","rid":"1000795","author":"网络","title":" 1","summary":"","picurl":null,"count":"39","create_time":"1511498710","status":"1","reply_number":"3","like":"0","isanonymous":"0","content_imgs":"","city":"河南省新乡市","nikename":"董小硕","avatar":"10,0c04fbec28b4"},{"id":"2754","rid":"1000808","author":"网络","title":"😁","summary":"","picurl":null,"count":"19","create_time":"1511337374","status":"1","reply_number":"1","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"},{"id":"2753","rid":"1000808","author":"网络","title":" ","summary":"","picurl":null,"count":"8","create_time":"1511337319","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"},{"id":"2752","rid":"1000808","author":"网络","title":"寻根问祖","summary":" ","picurl":null,"count":"0","create_time":"1511337103","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"},{"id":"2751","rid":"1000808","author":"网络","title":" ","summary":" ","picurl":null,"count":"17","create_time":"1511334897","status":"1","reply_number":"2","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"},{"id":"2750","rid":"1000808","author":"网络","title":" ","summary":" ","picurl":null,"count":"2","create_time":"1511334871","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"上海市","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"},{"id":"2749","rid":"1000801","author":"网络","title":"emm","summary":null,"picurl":null,"count":"0","create_time":"1511334040","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"惋","avatar":"9,0e46afca7c18"},{"id":"2748","rid":"1000808","author":"网络","title":"如何钓鱼","summary":null,"picurl":null,"count":"4","create_time":"1511333733","status":"1","reply_number":"0","like":"0","isanonymous":"0","content_imgs":"","city":"局域网","nikename":"海里试吃鱼","avatar":"14,1b7009a3319e"}]
     * serverTime : 2018-09-27 16:03:03
     */

    private int code;
    private String msg;
    private int totalPage;
    private String serverTime;
    private List<DataBean> data;

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 2762
         * rid : 1000797
         * author : 网络
         * title : 于氏
         * summary : 于氏家谱
         * picurl : null
         * count : 1
         * create_time : 1538023180
         * status : 1
         * reply_number : 0
         * like : 0
         * isanonymous : 0
         * content_imgs :
         * city : 中国
         * nikename : 东东
         * avatar : 11,0c36bc1e07ce
         */

        private String id;
        private String rid;
        private String author;
        private String title;
        private String summary;
        private Object picurl;
        private String count;
        private String create_time;
        private String status;
        private String reply_number;
        private String like;
        private String isanonymous;
        private String content_imgs;
        private String city;
        private String nikename;
        private String avatar;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", rid='" + rid + '\'' +
                    ", author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", picurl=" + picurl +
                    ", count='" + count + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", status='" + status + '\'' +
                    ", reply_number='" + reply_number + '\'' +
                    ", like='" + like + '\'' +
                    ", isanonymous='" + isanonymous + '\'' +
                    ", content_imgs='" + content_imgs + '\'' +
                    ", city='" + city + '\'' +
                    ", nikename='" + nikename + '\'' +
                    ", avatar='" + avatar + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRid() {
            return rid;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Object getPicurl() {
            return picurl;
        }

        public void setPicurl(Object picurl) {
            this.picurl = picurl;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReply_number() {
            return reply_number;
        }

        public void setReply_number(String reply_number) {
            this.reply_number = reply_number;
        }

        public String getLike() {
            return like;
        }

        public void setLike(String like) {
            this.like = like;
        }

        public String getIsanonymous() {
            return isanonymous;
        }

        public void setIsanonymous(String isanonymous) {
            this.isanonymous = isanonymous;
        }

        public String getContent_imgs() {
            return content_imgs;
        }

        public void setContent_imgs(String content_imgs) {
            this.content_imgs = content_imgs;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getNikename() {
            return nikename;
        }

        public void setNikename(String nikename) {
            this.nikename = nikename;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", totalPage=" + totalPage +
                ", serverTime='" + serverTime + '\'' +
                ", data=" + data +
                '}';
    }
}
