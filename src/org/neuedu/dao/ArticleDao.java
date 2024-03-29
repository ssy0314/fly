package org.neuedu.dao;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;

import java.util.List;

public interface ArticleDao {
    int saveArticle(Article article);
    int updatePayKissNumByUid(Article article);
    //查询置顶文章列表
    List<Article> getTopArticleList();

    //获取综合（综合，未结，已结，精华）列表
    List<Article> getTenMainArticleList(Integer tid);
    //回贴周榜
    List<Reply> getReplyTopArticleList();
    //本周热议
    List<Article> getHotReplyArticleList();

    Article serchArticleById(Integer id);
    int updateArticleViewsById(Integer id);
    int updateArticleReplynumById(Reply reply);


    //按照分类id查询文章
    List<Article> serchArticleByCateId(Integer id);


    //根据人物id查询人物发表过的文章

    List<Article> serchPublishedArticleByUid(Integer uid);

}
