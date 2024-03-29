package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.CollectionAndPublishedLoader;
import org.neuedu.bean.Homeloader;
import org.neuedu.bean.Indexloader;

import java.util.List;

public interface ArticleService {
    int publishArticle(Article article);

    //加载首页信息
    Indexloader loaderIndexInfo(Integer tid);
    Article loadArticle(Integer id);
    List<Article> hotReplyAticleInfo();


    Indexloader loaderIndexcateInfo(Integer id);


    //加载收藏与发布页信息
    CollectionAndPublishedLoader CollectionAndPublishedInfo(Integer uid);

    //加载个人首页信息
    Homeloader loaderHomeInfo(Integer uid);
}
