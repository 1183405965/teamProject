package cn.bookstore.category.dao.Impl;

import cn.bookstore.category.dao.CategoryDao;
import cn.bookstore.pojo.Category;

import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public Category toCategory(Map<String, Object> map) {
        Category category = null;
        return category; //
    }

    @Override
    public void add(Category category) {

    }

    @Override
    public void delete(String cid) {

    }

    @Override
    public void edit(Category category) {

    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public List<Category> findByParent(String pid) {
        return null;
    }
    //分类的持久层
/*一级分类没有父分类，二级分类有父分类*/

}
