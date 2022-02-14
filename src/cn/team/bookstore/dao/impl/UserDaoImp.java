package cn.team.bookstore.dao.impl;

import cn.team.bookstore.dao.BaseDao;
import cn.team.bookstore.dao.UserDao;
import cn.team.bookstore.dao.tools.DBPool;
import cn.team.bookstore.pojo.Admin;
import cn.team.bookstore.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImp implements UserDao {
    private BaseDao baseDao = new BaseDaoImp();

    @Override
    public Admin login(Admin admin) {
        String sql = "select adminId,adminname,adminpwd from t_admin where adminname=? and adminpwd=?";
        Admin admin1 = null;
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql, admin.getAdminname(), admin.getAdminpwd());
            if (rs != null && rs.next()) {
                admin1 = new Admin(rs.getString(1), rs.getString(2), rs.getString(3));
                rs.close();
            }
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {

                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return admin1;
    }


}


