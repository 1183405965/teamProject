package cn.bookstore.category.service.Impl;

import cn.bookstore.category.dao.CategoryDao;
import cn.bookstore.category.dao.Impl.CategoryDaoImpl;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.pojo.Category;

import java.sql.SQLException;
import java.util.List;


public class CategoryServiceImpl implements CategoryService {
        private CategoryDao categoryDao =  new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        try {
            return categoryDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> findByParent(String cid) {
        return categoryDao.findByParent(cid);
    }

    @Override
    public void add(Category category) throws SQLException {
        categoryDao.add(category);
    }

    @Override
    public void deleteParent(String cid) {
        categoryDao.deleteParent(cid);
    }

    @Override
    public void deleteChild(String cid) {
        categoryDao.deleteChild(cid);
    }



    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    @Override
    public List<Category> findParents() {
        return categoryDao.findParents();
    }

    @Override
    public Category load(String cid) {
        return categoryDao.load(cid);
    }
}
