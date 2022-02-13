package cn.bookstore.category.service.Impl;



import cn.bookstore.category.dao.BookDao;
import cn.bookstore.category.dao.Impl.BookDaoImpl;


public class BookServiceImpl implements BookDao {

private BookDao bookDao = new BookDaoImpl();
    @Override
    public int findBookCountByCategory(String cid) {
     int n =   bookDao.findBookCountByCategory(cid);
        return n;
    }

}
