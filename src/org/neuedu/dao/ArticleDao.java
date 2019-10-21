package org.neuedu.dao;

import org.neuedu.bean.Article;

import java.util.List;

public interface ArticleDao {
    int saveArticle(Article article);
    int updatePayKissNumByUid(Article article);
    //查询置顶文章列表
    List<Article> getTopArticleList();

    //获取综合（综合，未结，已结，精华）列表
    List<Article> getTenMainArticleList(Integer tid);
    //回贴周榜



    //本周热议

}
