package cn.bookstore.category.service.Impl;

import cn.bookstore.category.dao.CategoryDao;
import cn.bookstore.category.dao.Impl.CategoryDaoImpl;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.pojo.Category;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void delete(String cid) {

    }

    @Override
    public void edit(Category category) {

    }
}
