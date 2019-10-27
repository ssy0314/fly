package org.neuedu.dao;



import org.neuedu.bean.Collection;

import java.util.List;

public interface CollectionDao {
    //根据人物id查询人物发表过的文章

    List<Collection> serchCollectedArticleByUid(Integer uid);
}
