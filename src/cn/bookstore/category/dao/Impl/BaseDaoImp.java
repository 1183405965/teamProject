package cn.bookstore.category.dao.Impl;


import cn.bookstore.category.dao.BaseDao;
import cn.bookstore.pojo.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoImp implements BaseDao {
    private Object Category = new Category();

    @Override
    public ResultSet insert(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
            }//用来针对pst设置参数
        }
        pst.execute();
        return null;
    }

    @Override
    public ResultSet del(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        //排空判断
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
            }//用来针对pst设置参数
        }
        pst.execute();
        //pst.close(); //不能在这里释放资源
        return null;
    }

    @Override
    public  ResultSet query(Connection connection,String sql, Object... params ) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
                System.out.println(i);
            }//用来针对pst设置参数
        }
        ResultSet rs = pst.executeQuery();
        //这里释放出错，rs还没有传
        //pst.close();
        return rs;
    }


    @Override
    public void update(Connection connection,String sql, Object... params) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        //排空判断
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
            }//用来针对pst设置参数
        }
        pst.execute();
        //pst.close(); //不能在这里释放资源
    }

    @Override
    public Category load(Connection connection, String sql, Object... params) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(sql);
        //排空判断
        if (params != null){
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i+1,params[i]);
            }//用来针对pst设置参数
        }
        pst.execute();
        //pst.close(); //不能在这里释放资源
        return (cn.bookstore.pojo.Category) Category;
    }


}
