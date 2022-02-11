package cn.bookstore.category.service.Impl;

import cn.bookstore.category.dao.CategoryDao;
import cn.bookstore.category.dao.Impl.CategoryDaoImpl;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.pojo.Category;

import java.util.List;


public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();



    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    @Override
    public void delete(String cid) {
        categoryDao.delete(cid);
    }

    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }
}
