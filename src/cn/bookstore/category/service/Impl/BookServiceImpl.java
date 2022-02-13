package cn.bookstore.category.service.Impl;




import cn.bookstore.category.dao.BookDao;
import cn.bookstore.category.dao.Impl.BookDaoImpl;
import cn.bookstore.category.service.BookService;


public class BookServiceImpl implements BookService {

private BookDao bookDao = new BookDaoImpl();
    @Override
    public int findBookCountByCategory(String cid) {
     int n =   bookDao.findBookCountByCategory(cid);
        return n;
    }

}
