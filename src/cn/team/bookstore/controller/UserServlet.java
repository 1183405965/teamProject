package cn.team.bookstore.controller;
import cn.team.bookstore.pojo.Admin;
import cn.team.bookstore.pojo.User;
import cn.team.bookstore.service.UserService;
import cn.team.bookstore.service.impl.UserServiceImp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(name = "UserServlet",urlPatterns = "/user/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService=new UserServiceImp();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("adminname");
        String pwd = req.getParameter("adminpwd");
        if (username==null||username==""||pwd==null||pwd==""){
            String err="用户名及密码不能空";
            req.setAttribute("errinfo","error");
            req.getRequestDispatcher("../adminjsps/login.jsp").forward(req,resp);
            return;

        }
        Admin admin = new Admin(null, username, pwd);
        //执行登录的业务逻辑
        Admin admin1 = userService.login(admin);
        if (admin1==null){
            //重新登录
            String err="用户名及密码不正确";
            req.setAttribute("errinfo","error");
            req.getRequestDispatcher("../adminjsps/login.jsp").forward(req,resp);
            return;
        }
        //登录成功
        resp.sendRedirect("../adminjsps/admin/main.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}