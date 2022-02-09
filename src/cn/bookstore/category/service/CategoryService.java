package cn.bookstore.category.service;

import cn.bookstore.pojo.Category;

public interface CategoryService {
    /*添加分类*/
    void add(Category category);
    /*删除分类*/
    void delete(String cid);
    /*修改分类*/
    void edit(Category category);
}
