package org.neuedu.dao;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;

import java.util.List;

public interface ReplyDao {
    List<Reply> serchReplyByAid(Integer aid);
    int saveReplyInfo(Reply reply);

    //按照用户id查询评论
    List<Reply> serchReplyByUid(Integer uid);
}
