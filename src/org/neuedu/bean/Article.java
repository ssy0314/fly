package org.neuedu.bean;

import java.util.List;

public class Article {
    private Integer id;
    private String title;
    private Integer cid;
    private Boolean isEnd;
    private Boolean isTop;
    private Boolean isCream;
    private Integer replyNum;
    private Integer views;
    private Integer uid;
    private Integer payKiss;
    private String publishTime;
    private String updateTime;
    private String content;
    private Boolean status;
    private String catenameZh;
    private String replyTime;
    private List<Reply> replyList;

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyContent) {
        this.replyTime = replyContent;
    }

    public String getCatenameZh() {
        return catenameZh;
    }

    public void setCatenameZh(String catenameZh) {
        this.catenameZh = catenameZh;
    }

    // 作者信息
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getCream() {
        return isCream;
    }

    public void setCream(Boolean cream) {
        isCream = cream;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPayKiss() {
        return payKiss;
    }

    public void setPayKiss(Integer payKiss) {
        this.payKiss = payKiss;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
