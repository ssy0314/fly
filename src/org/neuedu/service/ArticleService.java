package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.Indexloader;

import java.util.List;

public interface ArticleService {
    int publishArticle(Article article);

    //加载首页信息
    Indexloader loaderIndexInfo(Integer tid);
    Article loadArticle(Integer id);
    List<Article> hotReplyAticleInfo();


    Indexloader loaderIndexcateInfo(Integer id);
}
