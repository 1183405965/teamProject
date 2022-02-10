package cn.team.bookstore.dao.tools;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBPool {
    private static DataSource dataSource;//连接池对象
    static {
        dataSource =new DruidDataSource();
        ((DruidDataSource)dataSource).setDriverClassName("com.mysql.jdbc.Driver");
        ((DruidDataSource) dataSource) .setUrl("jdbc:mysql://localhost:3306/purcharse?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        ((DruidDataSource)dataSource).setUsername("root");
        ((DruidDataSource)dataSource).setPassword("123456");
        ((DruidDataSource)dataSource).setMinIdle(5);
        ((DruidDataSource)dataSource).setMaxActive(10);
    }
    public Connection getConnection(){//返回连接对象
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        DBPool dbPool = new DBPool();
        System.out.println(dbPool);

    }
}
