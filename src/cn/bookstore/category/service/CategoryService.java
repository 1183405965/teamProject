package cn.bookstore.category.service;

import cn.bookstore.pojo.Category;

import java.util.List;


public interface CategoryService {

    /*查询出所有分类*/
    List<Category> findAll();
    /*添加分类*/
    void add(Category category);
    /*删除分类*/
    void delete(String cid);
    /*修改分类*/
    void edit(Category category);
}
