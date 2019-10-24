package org.neuedu.bean;

import java.util.List;

public class Indexloader {
    private List<Category>  categoryList;
    private List<Article>  topArticleList;
    private List<Article>  tenArticleList;
    private List<Reply>  replyTopArticleList;
    private List<Article>  hotReplyArticleList;

    public List<Reply> getReplyTopArticleList() {
        return replyTopArticleList;
    }

    public void setReplyTopArticleList(List<Reply> replyTopArticleList) {
        this.replyTopArticleList = replyTopArticleList;
    }

    public List<Article> getHotReplyArticleList() {
        return hotReplyArticleList;
    }

    public void setHotReplyArticleList(List<Article> hotReplyArticleList) {
        this.hotReplyArticleList = hotReplyArticleList;
    }




    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Article> getTopArticleList() {
        return topArticleList;
    }

    public void setTopArticleList(List<Article> topArticleList) {
        this.topArticleList = topArticleList;
    }

    public List<Article> getTenArticleList() {
        return tenArticleList;
    }

    public void setTenArticleList(List<Article> tenArticleList) {
        this.tenArticleList = tenArticleList;
    }
    //周榜
    //本周热议
}
