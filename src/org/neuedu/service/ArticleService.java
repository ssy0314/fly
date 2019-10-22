package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.Indexloader;

public interface ArticleService {
    int publishArticle(Article article);

    //加载首页信息
    Indexloader loaderIndexInfo(Integer tid);
    Article loadArticle(Integer id);
}
