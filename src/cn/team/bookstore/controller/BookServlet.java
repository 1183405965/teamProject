package cn.team.bookstore.controller;

import cn.team.bookstore.dao.tools.randomNumber;
import cn.team.bookstore.pojo.Book;
import cn.team.bookstore.pojo.Category;
import cn.team.bookstore.pojo.PageBean;
import cn.team.bookstore.service.BookService;
import cn.team.bookstore.service.UserService;
import cn.team.bookstore.service.impl.BookServiceImp;
import cn.team.bookstore.service.impl.UserServiceImp;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@javax.servlet.annotation.WebServlet(name = "BookServlet", urlPatterns = "/BookServlet",initParams = {@WebInitParam(name="pageList",value = "10")})
public class BookServlet extends javax.servlet.http.HttpServlet {
    private BookService bookService=new BookServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String bs=request.getParameter("bs");
        if (bs.equals("queryAll")) {
            queryAll(request, response);
        }
        if (bs.equals("findPage")){
            findPage(request, response);
        }
        if (bs.equals("details")){
            details(request, response);
        }
        if (bs.equals("findAuthor")){
            findAuthor(request, response);
        }
        if (bs.equals("findPress")){
            findPress(request, response);
        }

        if (bs.equals("delete")){
            deleteBook(request, response);
        }
        if (bs.equals("add")){
           addBook(request, response);
        }
        if (bs.equals("mod")){
            modBook(request, response);
        }
        if (bs.equals("queryByCri")){
            queryByCri(request, response);
        }

    }

    private void modBook(HttpServletRequest request, HttpServletResponse response) {
       // !bname || !currPrice || !price || !discount || !author || !press || !pid || !cid
        String bname=request.getParameter("bname");
        String currPrice=request.getParameter("currPrice");
        String price=request.getParameter("price");
        String discount=request.getParameter("discount");
        String author=request.getParameter("author");
        String press=request.getParameter("press");
        String pid=request.getParameter("pid");
        String cid=request.getParameter("cid");
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
//        private String bid;         //主键id
//        private String bname;       //书名
//        private String author;      //作者
//        private double price;       //原价
//        private double currPrice;   //现价
//        private double discount;    //折扣
//        private String press;       //出版社
//        private String publishtime; //出版时间
//        private int edition;        //版次
//        private int pageNum;        //页数
//        private int wordNum;        //字数
//        private String printtime;   //打印时间
//        private int booksize;       //大小
//        private String paper;       //纸质
//        private Category category;          //所属分类--关联分类  外键（orderby  排序）
//        private String image_w;     //大图路径
//        private String image_b;     //小图路径
        String bname=(String) request.getAttribute("bname");
        String author=(String) request.getAttribute("author");
        String price=(String) request.getAttribute("price");
        String currPrice=(String) request.getAttribute("currPrice");
        String discount=(String) request.getAttribute("discount");
        String press=(String) request.getAttribute("press");
        String publishtime=(String) request.getAttribute("publishtime");
        String edition=(String) request.getAttribute("edition");
        String pageNum=(String) request.getAttribute("pageNum");
        String wordNum=(String) request.getAttribute("wordNum");
        String printtime=(String) request.getAttribute("printtime");
        String booksize=(String) request.getAttribute("booksize");
        String paper=(String) request.getAttribute("paper");
        String image_w=(String) request.getAttribute("image_w");
        String image_b=(String) request.getAttribute("image_b");
        Book book=new Book(randomNumber.getStringRandom(22),bname,author,Double.valueOf(price),Double.valueOf(currPrice),Double.valueOf(discount),press,publishtime,Integer.valueOf(edition),Integer.valueOf(pageNum),Integer.valueOf(wordNum),printtime,Integer.valueOf(booksize),paper,null,image_w,image_b);
        try {
            bookService.addBook(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {

        String bid=request.getParameter("bid");
        //删除图书
        bookService.deleteBook(bid);
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void findAuthor(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String author=request.getParameter("author");
        String pageNow = findPageNow(request, response);
        String pageList = getServletConfig().getInitParameter("pageList");
        PageBean page1=(PageBean) request.getSession().getAttribute("PageBean");
        if (Integer.valueOf(pageNow)<1){
            pageNow="1";
        }
        if (page1!=null&&Integer.valueOf(pageNow)>page1.getTotalPage())
        {
            pageNow=page1.getTotalPage()+"";
        }
        PageBean page = bookService.findAuthor(author,pageNow,pageList);
        page.setPageList(Integer.valueOf(pageList));
        page.setUrl(getUrl(request));
        page.setVarPageNo(Integer.valueOf(pageNow));
        page.setTotalPage(page.getTotalPage());
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void findPress(HttpServletRequest request, HttpServletResponse response) {//按单个属性查找对应book
        String press=request.getParameter("press");
        String pageNow = findPageNow(request, response);
        String pageList = getServletConfig().getInitParameter("pageList");
        PageBean page1=(PageBean) request.getSession().getAttribute("PageBean");
        if (Integer.valueOf(pageNow)<1){
            pageNow="1";
        }
        if (page1!=null&&Integer.valueOf(pageNow)>page1.getTotalPage())
        {
            pageNow=page1.getTotalPage()+"";
        }
        PageBean page = bookService.findPress(press,pageNow,pageList);
        page.setPageList(Integer.valueOf(pageList));
        page.setUrl(getUrl(request));
        page.setVarPageNo(Integer.valueOf(pageNow));
        page.setTotalPage(page.getTotalPage());
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void details(HttpServletRequest request, HttpServletResponse response) {
        String bid=request.getParameter("bid");
        //查询该图书，返回book类型
        Book book = bookService.findBook(bid);
        //添加到session里
        request.getSession().setAttribute("book",book);
        //转发
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/desc.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findPage(HttpServletRequest request, HttpServletResponse response) {
        String pageNow = findPageNow(request, response);
        String pageList = getServletConfig().getInitParameter("pageList");
        String cid=request.getParameter("cid");
        PageBean page1=(PageBean) request.getSession().getAttribute("PageBean");
        if (Integer.valueOf(pageNow)<1){
            pageNow="1";
        }
        if (page1!=null&&Integer.valueOf(pageNow)>page1.getTotalPage())
        {
            pageNow=page1.getTotalPage()+"";
        }
        PageBean page = bookService.findPage(cid,pageNow,pageList);
        page.setPageList(Integer.valueOf(pageList));
        page.setUrl(getUrl(request));
        page.setVarPageNo(Integer.valueOf(pageNow));
        page.setTotalPage(page.getTotalPage());
        request.getSession().setAttribute("PageBean",page);
        try {
            request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String contentType = request.getContentType();
        int i = contentType.indexOf("multipart/form-data");
        if (i!=-1){
            upload(request,response);
            return;
        }
        doGet(request, response);
    }
   private void queryAll(HttpServletRequest request, HttpServletResponse response){
       List<Category> categories = bookService.queryAll();
       request.setAttribute("parents",categories);
       try {
           request.getRequestDispatcher("/adminjsps/admin/book/left.jsp").forward(request,response);
       } catch (ServletException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
   };
    private String findPageNow(HttpServletRequest request, HttpServletResponse response){
        String pageNow="1";
        String pageNow1=request.getParameter("pageNow");
        if (pageNow1!=null&&pageNow1!=""){
            return pageNow1;
        }
        return pageNow;
    }
    private String getUrl(HttpServletRequest request){
        String url=request.getRequestURI()+"?"+request.getQueryString();
        int index = url.indexOf("&pageNow=");
        if (index!=-1){
            url=url.substring(0,index);
        }
        return url;
    }
    private void upload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");    //设置编码

//    创建 FileItem 对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = getServletContext().getRealPath("/WEB-INF/upload");
        //指定临时文件目录
        factory.setRepository(new File(path));
        //设置内存缓冲区的大小
        factory.setSizeThreshold(1024*1024) ;
        //负责处理上传的文件数据，并将表单中每个输入项封装成一个 FileItem 对象中
        ServletFileUpload upload = new ServletFileUpload(factory);
//    //ProgressListener显示上传进度
//    ProgressListener progressListener = new ProgressListener() {
//        public void update(long pBytesRead, long pContentLength, int pItems) {
//            System.out.println("到现在为止,  " + pBytesRead + " 字节已上传，总大小为 "
//                    + pContentLength);
//        }
//    };
//    upload.setProgressListener(progressListener);

        List<FileItem> list;
        try {
            //调用Upload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
            list = (List<FileItem>)upload.parseRequest(request);
//      对list进行迭代，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
            for(FileItem item : list){
                String name = item.getFieldName();
                if(item.isFormField()){//为普通表单字段
                    String value = new String(item.getString().getBytes("iso-8859-1"),"utf-8") ;
                    request.setAttribute(name, value);
                }else{//为上传文件，则调用item.write方法写文件
                    String value = item.getName() ;
                    int start = value.lastIndexOf("\\");
                    String filename = value.substring(start+1);
                    request.setAttribute(name, filename);
                    item.write(new File(path,filename));
                }
            }
            //保存普通表单的数据
            addBook(request,response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void queryByCri(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageList = getServletConfig().getInitParameter("pageList");
        String bname=request.getParameter("bname");
        String auther=request.getParameter("auther");
        String press=request.getParameter("press");
        PageBean pageBean=(PageBean)request.getSession().getAttribute("PageBean");
        String pageNow=findPageNow(request,response);
        if (pageBean==null){
            pageBean=new PageBean();
        }
        if (Integer.valueOf(pageNow)<1){
            pageNow="1";
        }
        pageBean.setUrl(getUrl(request));
        pageBean.setVarPageNo(Integer.valueOf(pageNow));
        pageBean.setPageList(Integer.valueOf(pageList));
        try {
             pageBean = bookService.queryByCri(bname,auther,press,pageBean);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        pageBean.setTotalPage(pageBean.getTotalPage());
        request.getSession().setAttribute("PageBean",pageBean);
        request.getRequestDispatcher("/adminjsps/admin/book/list.jsp").forward(request,response);
    }
}