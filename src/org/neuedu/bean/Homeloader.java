package org.neuedu.bean;

import java.util.List;

public class Homeloader {
    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public List<Article> getPublishedList() {
        return publishedList;
    }

    public void setPublishedList(List<Article> publishedList) {
        this.publishedList = publishedList;
    }

    private List<Reply> replyList;
    private List<Article>  publishedList;
}
