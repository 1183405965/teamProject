package cn.bookstore.tools;

import com.alibaba.druid.pool.DruidDataSource;
/*数据库连接*/
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DBPool {
    private  static DataSource dataSource;

    static {
      //初始化的时候就创建数据库连接池对象
      dataSource = new DruidDataSource();//数据库连接池
        ((DruidDataSource) dataSource) .setDriverClassName("com.mysql.jdbc.Driver");
        ((DruidDataSource) dataSource) .setUrl("jdbc:mysql://localhost:3306/goods?useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        ((DruidDataSource) dataSource) .setUsername("root");
        ((DruidDataSource) dataSource) .setPassword("root");
    }

    public static void main(String[] args) {
        DBPool pool = new DBPool();
        System.out.println("连接成功"+pool.getConnection());
    }

    public   Connection getConnection()  {//返回链接对象
        try {
            return dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
