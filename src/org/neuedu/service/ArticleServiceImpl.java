package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.Category;
import org.neuedu.bean.Indexloader;
import org.neuedu.dao.ArticleDao;
import org.neuedu.dao.ArticleDaoImpl;
import org.neuedu.dao.CategoryDao;
import org.neuedu.dao.CategoryDaoImpl;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
   private ArticleDao articleDao = new ArticleDaoImpl();
   private CategoryDao categoryDao = new CategoryDaoImpl();
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
        //获取本周热议
       Indexloader indexloader = new Indexloader();
       indexloader.setCategoryList(categories);
       indexloader.setTenArticleList(tenMainArticleList);
       indexloader.setTopArticleList(topArticleList);
        return indexloader;
    }

    @Override
    public Article loadArticle(Integer id) {
        Article article = articleDao.serchArticleById(id);

        return article;
    }

}
