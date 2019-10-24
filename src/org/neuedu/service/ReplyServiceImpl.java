package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;
import org.neuedu.dao.ArticleDao;
import org.neuedu.dao.ArticleDaoImpl;
import org.neuedu.dao.ReplyDao;
import org.neuedu.dao.ReplyDaoImpl;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
    ReplyDao replyDao = new ReplyDaoImpl();
    ArticleDao articleDao = new ArticleDaoImpl();
    @Override
    public List<Reply> serchReplyById(Integer id) {
        List<Reply> list = replyDao.serchReplyByAid(id);

        return list;
    }

    @Override
    public int saveReply(Reply reply) {
        int i = replyDao.saveReplyInfo(reply);
        articleDao.updateArticleReplynumById(reply);
        return i;
    }
}
