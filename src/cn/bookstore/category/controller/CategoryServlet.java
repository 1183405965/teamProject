package cn.bookstore.category.controller;

import cn.bookstore.category.service.BookService;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.category.service.Impl.BookServiceImpl;
import cn.bookstore.category.service.Impl.CategoryServiceImpl;
import cn.bookstore.pojo.Category;
import cn.bookstore.pojo.CategoryBean;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/admin/CategoryServlet")
public class CategoryServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();
    private BookService bookDao = new BookServiceImpl();


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
        request.setAttribute("id2",cid);                    //二级分类的cid来加载Category
        List<Category> child = categoryService.load(cid);   //一级分类保存之给前台输出
        request.setAttribute("child", child);
        List<Category> parents = categoryService.findParents();
        request.setAttribute("parents",parents );   //所有的父类的
        request.getRequestDispatcher("../adminjsps/admin/category/edit2.jsp").forward(request,response);
    }
    /*根据children cid修改*/
    private void editChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");   //这里是id2 ，子类的cid  ,没有pid啊
        Category child = new Category();   //二级分类
        Category category = new Category();

        request.getSession().getAttribute("child");
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
//找父类一级分类
        child.setCid(pid);
        child.setCname(cname);
        child.setDesc(desc);
            category.setParent(category);
        child.setParent(category);//parent弄进去
        categoryService.edit(child);
        List<Category> parents = categoryService.findAll();
        request.getSession().setAttribute("parent",parents);
       // request.setAttribute("child", categoryService.findByParent(cid));
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
    }
    private void editParentPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.setAttribute("id",cid);
        List<Category> parent = categoryService.load(cid);
        request.setAttribute("editParent", parent);
        request.getRequestDispatcher("../adminjsps/admin/category/edit.jsp").forward(request,response);
    }
    private void editParent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Category category = new Category();
        String id = request.getParameter("cid");
        String cname = request.getParameter("cname");
        cname = new String(cname.getBytes("ISO-8859-1"),"UTF-8");
        String desc = request.getParameter("desc");
        category.setCid(id);
        category.setCname(cname);
        category.setDesc(desc);
        categoryService.edit(category);
        //  request.setAttribute("child", categoryService.findByParent(id)); //我修改父类失败了导致这里子类为空了，所以edit2可以出来数据了
        List<Category> parents = categoryService.findAll();
        request.getSession().setAttribute("parents",parents);
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        return;
    }
    private void addChildPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*任务是传递*/
        String pid = request.getParameter("pid");//当前点击的父分类id
        List<Category> parents = categoryService.findParents();         //没有子节点的父节点,
        request.setAttribute("pid", pid);
        request.setAttribute("parents", parents);
        request.getRequestDispatcher("../adminjsps/admin/category/add2.jsp").forward(request,response);
    }

    private void deleteChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");    //二级分类的id
        int cbookNum = bookDao.findBookCountByCategory(cid);    //查看二级分类下有没有书
        if(cbookNum > 0) {
            request.setAttribute("msg", "该子分类下还有图书，不能删除！");
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        } else {
            categoryService.deleteParent(cid);
            List<Category> parents = categoryService.findAll();
            request.getSession().setAttribute("parents",parents);
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
            return;
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
