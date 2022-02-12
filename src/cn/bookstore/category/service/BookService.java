package cn.bookstore.category.service;
/*耦合图书部分*/
public interface BookService {
    //返回当前分类下图书个数
    int findBookCountByCategory(String cid);
}
