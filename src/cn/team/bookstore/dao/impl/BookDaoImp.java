package cn.team.bookstore.dao.impl;

import cn.team.bookstore.dao.BaseDao;
import cn.team.bookstore.dao.BookDao;
import cn.team.bookstore.dao.tools.DBPool;
import cn.team.bookstore.dao.tools.randomNumber;
import cn.team.bookstore.pojo.Admin;
import cn.team.bookstore.pojo.Book;
import cn.team.bookstore.pojo.Category;
import cn.team.bookstore.pojo.PageBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImp implements BookDao {
    private BaseDao baseDao = new BaseDaoImp();

    @Override
    public List<Category> queryAll() {
        String sql = "select * from t_category where pid is null";
        List<Category> categorys = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                categorys.add(new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),children(rs.getString(1)))) ;
            }
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return categorys;
    }

    List<Category> children(String cid){
        String sql = "select * from t_category where pid="+cid;
        List<Category> categorys = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                categorys.add(new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),null)) ;
            }
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return categorys;

    }
    @Override
    public PageBean findPage(String cid,String pageNow, String pageList) {
        String sql1 = "select count(cid) from t_book where cid='"+cid+"'";
        int pageNow1 = Integer.valueOf(pageNow);
        int pageList1 = Integer.valueOf(pageList);
        String sql = "select * from t_book where cid='"+cid+"'"+" limit "+(pageNow1-1)*pageList1+","+pageList1;
        PageBean pageBean=new PageBean();
        List<Book> books=new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Double.valueOf(rs.getString(4)),
                        Double.valueOf(rs.getString(5)),
                        Double.valueOf(rs.getString(6)),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.valueOf(rs.getString(9)),
                        Integer.valueOf(rs.getString(10)),
                        Integer.valueOf(rs.getString(11)),
                        rs.getString(12),
                        Integer.valueOf(rs.getString(13)),
                        rs.getString(14),
                        findCategory(cid),
                        rs.getString(16),
                        rs.getString(17)
                )) ;
            }
            pageBean.setBeanList(books);
            pageBean.setTotalNumber(findCount(sql1));
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return pageBean;
    }

    @Override
    public Book findBook(String bid) {
        String sql = "select * from t_book where bid='"+bid+"'";
        Book book=null;
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            if (rs != null && rs.next()) {
                book=new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Double.valueOf(rs.getString(4)),
                        Double.valueOf(rs.getString(5)),
                        Double.valueOf(rs.getString(6)),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.valueOf(rs.getString(9)),
                        Integer.valueOf(rs.getString(10)),
                        Integer.valueOf(rs.getString(11)),
                        rs.getString(12),
                        Integer.valueOf(rs.getString(13)),
                        rs.getString(14),
                        null,
                        rs.getString(16),
                        rs.getString(17)
                ) ;
            }
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return book;
    }

    @Override
    public PageBean findAuthor(String author, String pageNow, String pageList) {
        String sql1 = "select count(cid) from t_book where author='"+author+"'";
        int pageNow1 = Integer.valueOf(pageNow);
        int pageList1 = Integer.valueOf(pageList);
        String sql = "select * from t_book where author='"+author+"'"+" limit "+(pageNow1-1)*pageList1+","+pageList1;
        PageBean pageBean=new PageBean();
        List<Book> books=new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Double.valueOf(rs.getString(4)),
                        Double.valueOf(rs.getString(5)),
                        Double.valueOf(rs.getString(6)),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.valueOf(rs.getString(9)),
                        Integer.valueOf(rs.getString(10)),
                        Integer.valueOf(rs.getString(11)),
                        rs.getString(12),
                        Integer.valueOf(rs.getString(13)),
                        rs.getString(14),
                        null,
                        rs.getString(16),
                        rs.getString(17)
                )) ;
            }
            pageBean.setBeanList(books);
            pageBean.setTotalNumber(findCount(sql1));
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return pageBean;
    }
    @Override
    public PageBean findPress(String press, String pageNow, String pageList) {
        String sql1 = "select count(cid) from t_book where press='"+press+"'";
        int pageNow1 = Integer.valueOf(pageNow);
        int pageList1 = Integer.valueOf(pageList);
        String sql = "select * from t_book where press='"+press+"'"+" limit "+(pageNow1-1)*pageList1+","+pageList1;
        PageBean pageBean=new PageBean();
        List<Book> books=new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Double.valueOf(rs.getString(4)),
                        Double.valueOf(rs.getString(5)),
                        Double.valueOf(rs.getString(6)),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.valueOf(rs.getString(9)),
                        Integer.valueOf(rs.getString(10)),
                        Integer.valueOf(rs.getString(11)),
                        rs.getString(12),
                        Integer.valueOf(rs.getString(13)),
                        rs.getString(14),
                        null,
                        rs.getString(16),
                        rs.getString(17)
                )) ;
            }
            pageBean.setBeanList(books);
            pageBean.setTotalNumber(findCount(sql1));
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return pageBean;
    }

    @Override
    public void deleteBook(String bid) {
        String sql = "delete from t_book where bid=?";
        Connection connection=new DBPool().getConnection();
        try {
            baseDao.update(connection, sql, bid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            connection=null;
        }
    }

    @Override
    public void addBook(Book book) throws SQLException {
        String sql="insert into t_book(bid,bname,author,price,currPrice,discount,press,publishtime,edition,pageNum,wordNum,printtime,booksize,paper,cid,image_w,image_b) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection=new DBPool().getConnection();
            baseDao.update(connection,sql, book.getBid(),book.getBname(),book.getAuthor(),book.getPrice(),book.getCurrPrice(),book.getDiscount(),book.getPress(),book.getPublishtime(),book.getEdition(),book.getPageNum(),book.getWordNum(),book.getPrinttime(),book.getBooksize(),book.getPaper(),"5F79D0D246AD4216AC04E9C5FAB3199E",book.getImage_w(),book.getImage_b());
            connection.close();
            connection=null;

    }

    @Override
    public PageBean queryByCri(String bname, String author, String press,PageBean pageBean) {
        String sql1 = "select count(cid) from t_book";
        int pageNow1 = Integer.valueOf(pageBean.getVarPageNo());
        int pageList1 = Integer.valueOf(pageBean.getPageList());
        String sql="select * from t_book";
        ResultSet rs=null;
        StringBuilder where=new StringBuilder("");
        if (bname!=null&&!bname.trim().equals("")){
            where.append(" where bname ="+bname);
        }
        if (author!=null&&!author.trim().equals("")) {
            if (where.length() != 0)
                where = where.append(" and author like '%" + author + "%'");
            else  where.append(" where author like '%" + author + "%'");
        }


        if (press!=null&&!press.trim().equals("")){
            if (where.length()!=0)
                where= where.append(" and press like '%"+press+"%'");
            else where.append(" where press like '%"+press+"%'");
        }
        sql1+=where;
        sql+=where;
        sql+=" limit "+(pageNow1-1)*pageList1+","+pageList1;
        List<Book> books=new ArrayList<>();
        Connection connection=new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                        books.add(new Book(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        Double.valueOf(rs.getString(4)),
                        Double.valueOf(rs.getString(5)),
                        Double.valueOf(rs.getString(6)),
                        rs.getString(7),
                        rs.getString(8),
                        Integer.valueOf(rs.getString(9)),
                        Integer.valueOf(rs.getString(10)),
                        Integer.valueOf(rs.getString(11)),
                        rs.getString(12),
                        Integer.valueOf(rs.getString(13)),
                        rs.getString(14),
                        null,
                        rs.getString(16),
                        rs.getString(17)
                )) ;
            }
            pageBean.setBeanList(books);
            pageBean.setTotalNumber(findCount(sql1));
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return pageBean;
    }

    public int findCount(String sql) {
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            if (rs != null && rs.next()) {
                return Integer.valueOf(rs.getString(1));
            }
        } catch (Exception throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
       return 0;
    }
    private Category findCategory(String cid){
        String sql = "select * from t_category where cid='"+cid+"'";
        Category category=null;
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            if (rs != null && rs.next()) {
                category=new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),null);
            }
        } catch (Exception throwables) {

            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return category;
    }
//    public List<Good> queryByPage(String pageNow, String pageSize) throws SQLException {
//        ResultSet rs=null;
//        int pageNow1 = Integer.valueOf(pageNow);
//        int pageSize1 = Integer.valueOf(pageSize);
//        String sql="select id,goodname,goodtype,price,pic from good limit "+(pageNow1-1)*pageSize1+","+pageSize;
//        Connection connection=new DBPool().getConnection();
//        List<Good> list=new ArrayList<>();
//        try {
//            rs = baseDao.query(connection, sql, null);
//            while (rs!=null&&rs.next()){
//                Good good=new Good(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        Double.valueOf(rs.getString(4)),
//                        rs.getString(5));
//                list.add(good);
//            }
//        } catch (SQLException throwables) {
//            throw throwables;
//        }finally {
//            rs.close();
//            connection.close();
//            connection=null;
//        }
//        return list;
//    }
}
