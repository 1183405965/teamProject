package cn.bookstore.category.dao;

import cn.bookstore.pojo.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CategoryDao {
    /*查询出所有分类*/
    List<Category> findAll() throws SQLException;
    /*通过一级分类查询出二级分类*/
    List<Category> findByParent(String cid);
    /*添加分类*/
    void add(Category category) throws SQLException;
    /*删除分类*/
    void deleteParent(String cid);
    /*修改分类*/
    void edit(Category category);


    /*查询所有一级分类*/





}
