package cn.bookstore.category.dao.Impl;

import cn.bookstore.category.dao.BaseDao;
import cn.bookstore.category.dao.BookDao;

import cn.bookstore.tools.DBPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDaoImpl implements BookDao{
    private BaseDao baseDao = new BaseDaoImp();
    @Override
    public int findBookCountByCategory(String cid) {
        String sql = "select count(*) from t_book where cid=  ' "+cid +"'";
        Connection connection = new DBPool().getConnection();
        ResultSet rs = null;
        int nums=0;
        try {
            connection.setAutoCommit(false);
            rs  = baseDao.query(connection,sql);
            connection.commit();
            if (rs != null && rs.next()) {
                nums = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nums;
    }
}
