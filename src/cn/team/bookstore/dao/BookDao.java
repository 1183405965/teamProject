package cn.team.bookstore.dao;

import cn.team.bookstore.pojo.Book;
import cn.team.bookstore.pojo.Category;
import cn.team.bookstore.pojo.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    List<Category> queryAll();//查询所有
    PageBean findPage(String cid,String pageNow, String pageList);
    Book findBook(String bid);
    PageBean findAuthor(String author,String pageNow, String pageList);
    PageBean findPress(String press,String pageNow, String pageList);//详情查询分页
    void deleteBook(String bid);
    void addBook(Book book) throws SQLException;
    PageBean queryByCri(String bname,String author, String press,PageBean pageBean);
}
