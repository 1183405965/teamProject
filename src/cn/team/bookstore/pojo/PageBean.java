package cn.team.bookstore.pojo;

import java.util.List;
/*
* 分页Bean，它会在各层传递
*
* */
public class PageBean<T> {
    private  int varPageNo;//当前页码
    private  int totalNumber;//总记录数
    private  int pageList;//每页记录数
    private  String url;//请求路径和参数
    private List<T> beanList;//数据
    private int totalPage;//总页数
    public int getTotalPage(){//得到总页数
        int totalPage=totalNumber/pageList;
        return totalNumber%pageList==0?totalPage:totalPage+1;
    }

    public PageBean(int varPageNo, int totalNumber, int pagelist, String url, List<T> beanList,int totalPage) {
        this.varPageNo = varPageNo;
        this.totalNumber = totalNumber;
        this.pageList = pagelist;
        this.url = url;
        this.beanList = beanList;
        this.totalPage = totalPage;

    }


    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getVarPageNo() {
        return varPageNo;
    }

    public void setVarPageNo(int varPageNo) {
        this.varPageNo = varPageNo;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getPageList() {
        return pageList;
    }

    public void setPageList(int pagelist) {
        this.pageList = pagelist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<T> beanList) {
        this.beanList = beanList;
    }

    public PageBean() {
    }
    public void beanListClear(){
        beanList.clear();
    }

}
