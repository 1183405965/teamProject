package cn.bookstore.pojo;

import java.util.List;
/*分类模块的实体类--双向自身关联，pid的父分类关联子分类，子分类关联父分类*/
public class Category {
    private String cid;         // 主键
    private String cname;       // 分类名称
    private Category parent;    // 父分类 (pid自身关联当外键了，为空为父类)
    private String desc;        // 分类描述
    private List<Category> children;// 子分类（从面向对象考虑，有爸爸的）



    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }


    public Category() {
    }

    public Category(String cid, String cname, Category parent, String desc, List<Category> children) {
        this.cid = cid;
        this.cname = cname;
        this.parent = parent;
        this.desc = desc;
        this.children = children;
    }
}
