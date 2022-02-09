package cn.bookstore.category.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseDao {
    ResultSet insert(Connection connection, String sql, Object...params) throws SQLException;//插入数据
    ResultSet del(Connection connection, String sql, Object...params) throws SQLException;
    ResultSet query(Connection connection,String sql, Object...params) throws SQLException;
    void update(Connection connection,String sql,Object...params) throws SQLException;
}
