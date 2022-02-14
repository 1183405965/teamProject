package cn.team.bookstore.service.impl;

import cn.team.bookstore.dao.UserDao;
import cn.team.bookstore.dao.impl.UserDaoImp;
import cn.team.bookstore.pojo.Admin;
import cn.team.bookstore.pojo.User;
import cn.team.bookstore.service.UserService;

import java.sql.SQLException;

public class UserServiceImp implements UserService {
    private UserDao userDao=new UserDaoImp();
    @Override
    public Admin login(Admin admin) {
        //通过查询t_admin表来确定用户是否合法
        return userDao.login(admin);
    }

}
