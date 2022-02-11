package cn.bookstore.category.controller;

import cn.bookstore.category.service.CategoryService;
import cn.bookstore.category.service.Impl.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", urlPatterns = "/admin/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    //查找出所有的category传到adminjsps/admin/category/list.jsp
    private CategoryService categoryService = new CategoryServiceImpl();
    public String findAll(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        request.setAttribute("parents", categoryService.findAll());
        return "f:/adminjsps/admin/category/list.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");//处理响应编码
        String bs = request.getParameter("method");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
