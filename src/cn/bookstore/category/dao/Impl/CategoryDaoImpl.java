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
    /*只查找父类的*/
public List<Category> findParents()  {
    /*
     * 1. 查询出所有一级分类
     */
    Connection connection = new DBPool().getConnection();
    String sql = "select * from t_category where pid is null order by orderBy";
    ResultSet mapList = null;
    List<Category> list = new ArrayList();
    try {
        connection.setAutoCommit(false);
        mapList = baseDao.query(connection,sql);
        connection.commit();
        while (mapList != null && mapList.next()) {
            list.add(new Category(mapList.getString(1),mapList.getString(2),null,mapList.getString(4),  findByParent(mapList.getString(1)) ));
        }
        if (mapList==null){
            return null;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

    @Override
    public List<Category> load(String cid) {
        String sql = "select * from t_category where cid = '"+cid+"'";
        Connection connection = new DBPool().getConnection();
        List<Category> load = new ArrayList();
        ResultSet rs = null;

        try {
            connection.setAutoCommit(false);
            rs = baseDao.query(connection, sql);
            connection.commit();
            while (rs != null && rs.next()) {
                load.add(new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),  findByParent(rs.getString(1)) ));
            }
            if (rs==null){
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return  load;
    }

    @Override
    public List<Category> findAll() throws SQLException {       /*查询所有pid不为空的父级(一级分类)*/
        String sql = " select * from t_category where pid is null order by orderBy ";
        Connection connection = new DBPool().getConnection();
        //先查找一级分类，，在遍历它的子分类
        List<Map<String,Object>> maplist = new ArrayList<>();   //没有二级分类的集合
        ResultSet rs = null;    //查询结果
        List<Category> parents = new ArrayList<>(); //最终的一级分类集合
        List<Category> findByParent =new ArrayList<>(); //最终的二级分类集合
        try {
            rs = baseDao.query(connection,sql);
            while (rs != null && rs.next()) {
                parents.add(new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),  findByParent(rs.getString(1)) ));
            }
            if (rs==null){
                return null;
            }
/*            // parents = toCategory(maplist); //父分类有子分类了
            for (Category parent: parents) { *//*查出父分类的子分类*//*
                 children =findByParent(parent.getCid());  //cid找
                    //子类添加到parents
                parent.setChildren(children);
            }      */
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            rs.close();
            connection.close();
            connection = null;
        }
        return parents;
    }



    @Override
    public List<Category> findByParent(String cid) {
        String sql = " select * from t_category where pid = '"+cid+"'" + " order by orderBy";   /*加了单引号查不到二级，不加单引号不识别uuid添加的主键的一级分类*/
        List<Category> childrens = new ArrayList<>();
        ResultSet rs = null;
        Connection connection = new DBPool().getConnection();
        try {
            rs = baseDao.query(connection, sql);
            while (rs != null && rs.next()) {
                childrens.add(new Category(rs.getString(1),rs.getString(2),null,rs.getString(4),null)) ;
            }
            if (rs==null){
                return null;
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                connection.close();
                connection = null;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return childrens;
    }
    //分类的持久层
    /*一级分类没有父分类，二级分类有父分类*/

    @Override
    public void add(Category category)  {
        String sql = "insert into t_category(cid,cname,pid,`desc`) values(?,?,?,?)";
        Connection connection = new DBPool().getConnection();
        /* 因为一级分类，没有parent，而二级分类有！要兼容两次分类，所以需要判断         */
        String pid = null;//一级分类
        if(category.getParent() != null) {   //如果没有父分了
            pid = category.getParent().getCid();
            category.getParent().getCid();
        }
        try {
            connection.setAutoCommit(false);
            baseDao.insert(connection, sql,category.getCid(), category.getCname(), pid, category.getDesc());
            connection.commit();
            //回滚
        } catch (SQLException e) {

            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }



    @Override
    public void deleteParent(String cid) {
        /*根据id删除id*/
        String sql = "delete from t_category where cid = '"+cid+"'";
        Connection connection = new DBPool().getConnection();
        /*看出下面是否有子类*/
        try {
            connection.setAutoCommit(false);
            baseDao.del(connection,sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteChild(String cid) {
        /*根据id删除id*/
        String sql = "delete from t_category where cid = '"+cid+"'";
        Connection connection = new DBPool().getConnection();
        /*看出下面是否有子类*/
        try {
            connection.setAutoCommit(false);
            baseDao.del(connection,sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void edit(Category category) {
        String sql = "update t_category set cname=?, pid=?, `desc`=? where cid=? ;";
        Connection connection = new DBPool().getConnection();
        try {
            connection.setAutoCommit(false);
            baseDao.update(connection,sql,category.getCname(),category.getParent(),category.getDesc(),category.getCid());
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
