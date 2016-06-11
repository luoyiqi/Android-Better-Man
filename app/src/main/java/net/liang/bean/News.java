package net.liang.bean;

/**
 * Created by lianghuiyong on 2016/6/10.
 */
public class News {
    private String ID;
    private String time;
    private String title;
    private String body;
    private String commentCount;    //评论数
    private String author;          //作者
    private String authorid;        //作者ID
    private String url;             //资讯Url地址

    private String type;            //资讯的类型
    private String authoruid2 ;     //作者ID
    private String eventurl;        //活动链接地址

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthoruid2() {
        return authoruid2;
    }

    public void setAuthoruid2(String authoruid2) {
        this.authoruid2 = authoruid2;
    }

    public String getEventurl() {
        return eventurl;
    }

    public void setEventurl(String eventurl) {
        this.eventurl = eventurl;
    }
}
