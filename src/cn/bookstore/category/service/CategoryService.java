package cn.bookstore.category.service;

import cn.bookstore.pojo.Category;

import java.sql.SQLException;
import java.util.List;


public interface CategoryService {

    /*查询出所有分类*/
    List<Category> findAll();
    List<Category> findByParent(String cid);
    /*添加分类*/
    void add(Category category) throws SQLException;
    /*删除分类*/
    void deleteParent(String cid);
    /*删除分类*/
    void deleteChild(String cid);
    /*修改分类*/
    void edit(Category category);


    List<Category> findParents();

    Category load(String cid);
}
