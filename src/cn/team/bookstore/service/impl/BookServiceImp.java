package cn.team.bookstore.service.impl;

import cn.team.bookstore.dao.BookDao;
import cn.team.bookstore.dao.impl.BookDaoImp;
import cn.team.bookstore.pojo.Book;
import cn.team.bookstore.pojo.Category;
import cn.team.bookstore.pojo.PageBean;
import cn.team.bookstore.service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImp implements BookService {
    private BookDao bookDao= new BookDaoImp();

    @Override
    public List<Category> queryAll() {
        return bookDao.queryAll();
    }

    @Override
    public PageBean findPage(String id,String pageNow, String pageList) {
        return bookDao.findPage(id,pageNow,pageList);
    }

    @Override
    public Book findBook(String bid) {
        return bookDao.findBook(bid);
    }

    @Override
    public PageBean findAuthor(String author, String pageNow, String pageList) {
        return bookDao.findAuthor(author, pageNow, pageList);
    }

    @Override
    public PageBean findPress(String press, String pageNow, String pageList) {
        return bookDao.findPress(press, pageNow, pageList);
    }

    @Override
    public void deleteBook(String bid) {
        bookDao.deleteBook(bid);
    }

    @Override
    public void addBook(Book book) throws SQLException {
      bookDao.addBook(book);
    }

    @Override
    public PageBean queryByCri(String bname, String author, String press,PageBean pageBean) {
        return bookDao.queryByCri(bname, author, press,pageBean);
    }


}
