package org.neuedu.service;

import org.neuedu.bean.*;
import org.neuedu.dao.*;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
   private ArticleDao articleDao = new ArticleDaoImpl();
   private CategoryDao categoryDao = new CategoryDaoImpl();
   private ReplyDao replyDao = new ReplyDaoImpl();
    private  CollectionDao collectionDao = new CollectionDaoImpl();
   @Override
    public int publishArticle(Article article) {
        int i = articleDao.saveArticle(article);
        if(i!=0){
            articleDao.updatePayKissNumByUid(article);
            return 1;
        }
        return 0;
    }

    @Override
    public Indexloader loaderIndexInfo(Integer tid) {
       //获取文章分类信息
        List<Category> categories = categoryDao.serchAllCateListByRole();
        //获取置顶信息
        List<Article> topArticleList = articleDao.getTopArticleList();
        //获取主要文章信息
        List<Article> tenMainArticleList = articleDao.getTenMainArticleList(tid);
        //获取回贴周榜
        List<Reply> replyTopArticleList = articleDao.getReplyTopArticleList();
        //获取本周热议
        List<Article>  hotReplyArticleList = articleDao.getHotReplyArticleList();
        Indexloader indexloader = new Indexloader();
       indexloader.setCategoryList(categories);
       indexloader.setTenArticleList(tenMainArticleList);
       indexloader.setTopArticleList(topArticleList);
       indexloader.setHotReplyArticleList(hotReplyArticleList);
       indexloader.setReplyTopArticleList(replyTopArticleList);
        return indexloader;
    }

    @Override
    public Article loadArticle(Integer id) {
        Article article = articleDao.serchArticleById(id);
        List<Reply> list = replyDao.serchReplyByAid(id);
        article.setReplyList(list);
        articleDao.updateArticleViewsById(id);
        return article;
    }

    @Override
    public List<Article> hotReplyAticleInfo() {
        return articleDao.getHotReplyArticleList();
    }

    @Override
    public Indexloader loaderIndexcateInfo(Integer id) {
        //获取文章分类信息
        List<Category> categories = categoryDao.serchAllCateListByRole();
        //获取主要文章信息
        List<Article> tenMainArticleList = articleDao.serchArticleByCateId(id);
        //获取本周热议
        List<Article>  hotReplyArticleList = articleDao.getHotReplyArticleList();
        Indexloader indexloader = new Indexloader();
        indexloader.setCategoryList(categories);
        indexloader.setTenArticleList(tenMainArticleList);
        indexloader.setHotReplyArticleList(hotReplyArticleList);
        return indexloader;
    }

    @Override
    public CollectionAndPublishedLoader CollectionAndPublishedInfo(Integer uid) {

        CollectionAndPublishedLoader loader = new CollectionAndPublishedLoader();
        List<Article> publishedList = articleDao.serchPublishedArticleByUid(uid);
        List<Collection> collectedList = collectionDao.serchCollectedArticleByUid(uid);
       loader.setCollectionList(collectedList);
       loader.setPublishedList(publishedList);

       return loader;
    }

    @Override
    public Homeloader loaderHomeInfo(Integer uid) {
        Homeloader loader = new Homeloader();
        List<Article> articles = articleDao.serchPublishedArticleByUid(uid);
        List<Reply> replies = replyDao.serchReplyByUid(uid);
        loader.setPublishedList(articles);
        loader.setReplyList(replies);
        return loader;

    }

}
