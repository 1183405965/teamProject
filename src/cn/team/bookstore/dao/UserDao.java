package cn.team.bookstore.dao;
import cn.team.bookstore.pojo.Admin;
import java.sql.SQLException;

public interface UserDao {
    Admin login(Admin admin);

}
