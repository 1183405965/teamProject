package cn.team.bookstore.service;

import cn.team.bookstore.pojo.Book;
import cn.team.bookstore.pojo.Category;
import cn.team.bookstore.pojo.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<Category> queryAll();//查询所有
    PageBean findPage(String cid,String pageNow, String pageList);//查询一个完整的页面记录
    Book findBook(String bid);
    PageBean findAuthor(String author,String pageNow, String pageList);
    PageBean findPress(String press,String pageNow, String pageList);//详情查询分页
    void deleteBook(String bid);
    void addBook(Book book) throws SQLException;
    PageBean queryByCri(String bname,String author, String press,PageBean pageBean);

}
