package org.neuedu.dao;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> serchReplyByAid(Integer aid);
    int saveReplyInfo(Reply reply);
}
