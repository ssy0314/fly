package org.neuedu.bean;

public class Category {
    private Integer id;
    private String cateName;
    private String cateNameZh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateNameZh() {
        return cateNameZh;
    }

    public void setCateNameZh(String cateNameZh) {
        this.cateNameZh = cateNameZh;
    }
}
