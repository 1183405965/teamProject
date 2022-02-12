package cn.bookstore.category.controller;

import cn.bookstore.category.dao.BookDao;
import cn.bookstore.category.dao.Impl.BookDaoImpl;
import cn.bookstore.category.service.CategoryService;
import cn.bookstore.category.service.Impl.CategoryServiceImpl;
import cn.bookstore.pojo.Category;
import cn.bookstore.pojo.CategoryBean;
import javafx.scene.Parent;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
            request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
        }

    }
/*根据children cid修改*/
    private void editChild(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = new Category();
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");

         categoryService.edit(category);

        request.setAttribute("child", categoryService.findByParent(cid));
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

    }

    private void editParent(HttpServletRequest request, HttpServletResponse response) {

    }

    /*添加二级分类*/
    private void addChild(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=gb2312");
        Category children = new Category();
        children.setCid(CategoryBean.uuid());//设置cid
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        String pid = request.getParameter("pid"); //一级分类
        children.setCname(cname);
        children.setDesc(desc);
        try {
            categoryService.add(children);
            request.getSession().setAttribute("childrens",children);
            categoryService.findAll();
            response.sendRedirect("../adminjsps/admin/category/list.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("errinfo","添加二级分类失败");
        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);

    }

    /*添加一级分类*/
    private void addParent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=gb2312");

        Category Parent = new Category();
        String cname = request.getParameter("cname");
        String desc = request.getParameter("desc");
        Parent.setCid(CategoryBean.uuid());//设置cid
        Parent.setCname(cname);
        Parent.setDesc(desc);
        try {
            categoryService.add(Parent);
            request.getSession().setAttribute("parents",Parent);
            categoryService.findAll();
            response.sendRedirect("../adminjsps/admin/category/list.jsp");
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
        doGet(request, response);

    }



    //通过CategoryService查找出所有的category传到adminjsps/admin/category/list.jsp  parents保存到session
    public void findAll(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        List<Category> parents = categoryService.findAll();
        request.setAttribute("parents", parents);

        request.getRequestDispatcher("../adminjsps/admin/category/list.jsp").forward(request,response);
       // return "/adminjsps/admin/category/list.jsp";

    }

}
