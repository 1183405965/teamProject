package cn.team.bookstore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface BaseDao {
    //通用查询方法，返回结果集
    ResultSet query(Connection connection, String sql, Object... params) throws SQLException;

    //执行增删改方法
    void update(Connection connection,String sql,Object... params) throws SQLException;

}
