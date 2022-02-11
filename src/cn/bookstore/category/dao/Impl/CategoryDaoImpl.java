package cn.bookstore.category.dao.Impl;

import cn.bookstore.category.dao.BaseDao;
import cn.bookstore.category.dao.CategoryDao;
import cn.bookstore.pojo.Category;
import cn.bookstore.pojo.CategoryBean;
import cn.bookstore.tools.DBPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDaoImpl implements CategoryDao {
    private BaseDao baseDao = new BaseDaoImp();



           /*一级分类里包含子分类，1对n 将其映射成多个的listcategory数组集合*/
    public List<Category> toCategory(List<Map<String,Object>> maplist) {   /*传入map键值对 */
        List<Category> categoryList = new ArrayList<Category>();//创建一个空集合。分类
        /*for each遍历将每个Map(这样就不会重复)转换成一个分类添加到集合里*/
        for (Map<String, Object> map:maplist ) {
            Category c = CategoryBean.toCategory(map);
            categoryList.add(c);
        }
        return categoryList; //
    }
/*把一个Map中的数据映射带Category中--在CategoryBean中*/


    @Override
    public List<Category> findAll() {       /*查询所有pid不为空的父级(一级分类)*/
        String sql = "select * from t_category where pid is null order by orderBy";
        Connection connection = new DBPool().getConnection();
        //先查找一级分类，，在遍历它的子分类
        List<Map<String,Object>> maplist = new ArrayList<>();   //没有二级分类的集合
        ResultSet rs = null;    //查询结果
        List<Category> parents = null; //最终的一级分类集合
        try {
            rs = baseDao.query(connection,sql,null);
            while (rs != null && rs.next()) {
                maplist.add((Map<String, Object>) rs); //所有的一级分类
            }
             parents = toCategory(maplist); //父分类有子分类了
            for (Category parent: parents) { /*查出父分类的子分类*/
                List<Category> children =findByParent(parent.getCid());  //cid找
                    //子类添加到parents
                parent.setChildren(children);
            }            /*将parents存入session*/
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return parents;
    }
    @Override
    public List<Category> findByParent(String pid) {
        String sql = "select * from t_category where pid is ？";
        Connection connection = new DBPool().getConnection();
        //子分类的查找
        List<Map<String,Object>> maplist = new ArrayList<>();
        ResultSet rs = null;    //查询结果
        try {
            rs = baseDao.query(connection,sql,null);
            while (rs != null && rs.next()) {
                maplist.add((Map<String, Object>) rs); //所有的二级分类
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return toCategory(maplist);   //转换

    }
    //分类的持久层
    /*一级分类没有父分类，二级分类有父分类*/

    @Override
    public void add(Category category) {
        String sql = "insert into t_category(cid,cname,pid,`desc`) values(?,?,?,?)";
        Connection connection = new DBPool().getConnection();
        /*
         * 因为一级分类，没有parent，而二级分类有！
         * 我们这个方法，要兼容两次分类，所以需要判断
         */
        String pid = null;//一级分类
        if(category.getParent() != null) {
            pid = category.getParent().getCid();
        }
        Object[] params = {category.getCid(), category.getCname(), pid, category.getDesc()};
       // categoryDao.add(sql, params);
        try {
            connection.setAutoCommit(false);
            baseDao.update(connection, sql,params);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(String cid) {
        /*根据id删除id*/
        String sql = "delete from t_category where cid=?";
        Connection connection = new DBPool().getConnection();
    }

    @Override
    public void edit(Category category) {
        String sql = "update good set goodname = ?,goodtype = ?,price = ? where id  = ? ;";
        Connection connection = new DBPool().getConnection();
    }




}
