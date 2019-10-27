package org.neuedu.bean;

import java.util.List;

public class CollectionAndPublishedLoader {
    private List<Collection> collectionList;
    private List<Article>  publishedList;

    public List<Collection> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
    }

    public List<Article> getPublishedList() {
        return publishedList;
    }

    public void setPublishedList(List<Article> replyList) {
        this.publishedList = replyList;
    }
}
