package cn.bookstore.category.dao;

import cn.bookstore.pojo.Category;

import java.util.List;
import java.util.Map;

public interface CategoryDao {
    Category toCategory(Map<String,Object> map);
    /*添加分类*/
    void add(Category category);
    /*删除分类*/
    void delete(String cid);
    /*修改分类*/
    void edit(Category category);

    /*查询出所有分类*/
    List<Category> findAll();
    /*查询所有一级分类*/

    /*通过一级分类查询出二级分类*/
    List<Category> findByParent(String pid);


}
