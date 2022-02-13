package cn.bookstore.category.controller;

import cn.bookstore.category.dao.BookDao;
import cn.bookstore.category.dao.Impl.BookDaoImpl;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.category.service.Impl.CategoryServiceImpl;
import cn.bookstore.pojo.Category;
import cn.bookstore.pojo.CategoryBean;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/admin/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();
    private BookDao bookDao = new BookDaoImpl();


    /*需要完成的业务
     *   添加一二级分类
     *   删除一二级分类
     *   修改一二级分类
     *   删除分类的时候：
     *           二级可以直接删，一级需要删除全部的书之后才能删*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");//处理响应编码
        String bs = request.getParameter("method");     //通过请求方法
        String bs2 = request.getParameter("method2");     //预处理
        if ("findAll".equals(bs)){
            findAll(request,response);
        }else if ("addParent".equals(bs)){
            addParent(request,response);
        }else if ("addChild".equals(bs)){
            addChild(request,response);
        }else if ("editParent".equals(bs)){
            editParent(request,response);
        }else if ("editChild".equals(bs)){
            editChild(request,response);
        }else if ("deleteParent".equals(bs)){
            deleteParent(request,response);
        }else if ("deleteChild".equals(bs)){
            deleteChild(request,response);
        }
        if ("addChildPre".equals(bs2)){
            addChildPre(request,response);
        }else if ("editParentPre".equals(bs2)){
            editParentPre(request,response);
        }else if ("editChildPre".equals(bs2)){
            editChildPre(request,response);
        }

    }

    private void editChildPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category child = categoryService.load(cid);
        request.setAttribute("child", child);
        request.setAttribute("parents", categoryService.findParents());
        request.getRequestDispatcher("../adminjsps/admin/category/edit2.jsp").forward(request,response);

    }

    private void editParentPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category parent = categoryService.load(cid);
        request.setAttribute("parent", parent);
        request.getRequestDispatcher("../adminjsps/admin/category/edit.jsp").forward(request,response);

    }

    private void addChildPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*任务是传递*/
        String pid = request.getParameter("pid");//当前点击的父分类id
        List<Category> parents = categoryService.findParents();         //没有子节点的父节点
        request.setAttribute("pid", pid);
        request.setAttribute("parents", parents);
        request.getRequestDispatcher("../adminjsps/admin/category/add2.jsp").forward(request,response);

    }

    private void deleteChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        int cbookNum = bookDao.findBookCountByCategory(cid);
        if(cbookNum > 0) {
            request.setAttribute("msg", "该子分类下还有图书，不能删除！");
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        } else {
            categoryService.deleteParent(cid);
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        }

    }

    private void deleteParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        List<Category> childrenNum = categoryService.findByParent(cid);
        if(childrenNum.size() > 0) {
            request.setAttribute("msg", "该分类下还有子分类，不能删除！");
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        } else {
            categoryService.deleteParent(cid);
            List<Category> parents = categoryService.findAll();
            request.setAttribute("parents", parents);
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

        }

    }
/*根据children cid修改*/
    private void editChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = new Category();
        String cname = request.getParameter("cname");

        cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
        String desc = request.getParameter("desc");
        category.setCname(cname);
        category.setDesc(desc);
         categoryService.edit(category);

        request.setAttribute("child", categoryService.findByParent(cid));
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

    }

    private void editParent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String cid = request.getParameter("cid");
        Category category = new Category();
        String cname = request.getParameter("cname");
        cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
        String desc = request.getParameter("desc");
        category.setCname(cname);
        category.setDesc(desc);
        categoryService.edit(category);
     //   request.setAttribute("child", categoryService.findByParent(cid)); //我修改父类失败了导致这里子类为空了，所以edit2可以出来数据了
        List<Category> parents = categoryService.findAll();
        request.getSession().setAttribute("parents",parents);
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

    }

    /*添加二级分类*/
    private void addChild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=gb2312");
        Category children = new Category();
        //获得表单数据
        String cname = request.getParameter("cname");
        cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
        String desc = request.getParameter("desc");
        String pid = request.getParameter("pid"); //一级分类
        Category parent = new Category();
        parent.setCid(pid);
        children.setCid(CategoryBean.uuid());
        children.setParent(parent);
        children.setCname(cname);
        children.setDesc(desc);
        //session预处理转发到add2的里面
        try {
            categoryService.add(children);
         //   request.getSession().setAttribute("childrens",children);
            List<Category> parents = categoryService.findAll();
            request.setAttribute("parents", parents);
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
/*
        request.setAttribute("errinfo","添加二级分类失败");
        List<Category> parents = categoryService.findAll();
        request.setAttribute("parents", parents);
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
*/

    }

    /*添加一级分类*/
    private void addParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");
        Category Parent = new Category();
        String cname = request.getParameter("cname");
        cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
        String desc = request.getParameter("desc");
        Parent.setCid(CategoryBean.uuid());//设置cid
        Parent.setCname(cname);
        Parent.setDesc(desc);
        try {
            categoryService.add(Parent);
             List<Category> parents = categoryService.findAll();
            request.getSession().setAttribute("parents",parents);
          //  response.sendRedirect("../adminjsps/admin/category/list.jsp");
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //request.getSession().setAttribute("parents", Parent);
        request.setAttribute("errinfo","添加一级分类失败");
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*表单提交的请求也去get里了*/
        doGet(request, response);
    }



    //通过CategoryService查找出所有的category传到adminjsps/admin/category/list.jsp  parents保存到session
    public void findAll(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<Category> parents = categoryService.findAll();
        request.setAttribute("parents", parents);
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);//请求转发，有数据
    }

}
