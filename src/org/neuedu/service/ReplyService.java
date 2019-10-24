package org.neuedu.service;

import org.neuedu.bean.Article;
import org.neuedu.bean.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> serchReplyById(Integer id);
    int saveReply(Reply reply);
}
