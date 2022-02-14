package cn.team.bookstore.pojo;


//管理员
public class Admin {
    private String adminId;     //主键id
    private String adminname;   //管理员账号
    private String adminpwd;    //管理员密码

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdminpwd() {
        return adminpwd;
    }

    public void setAdminpwd(String adminpwd) {
        this.adminpwd = adminpwd;
    }

    public Admin(String adminId, String adminname, String adminpwd) {
        this.adminId = adminId;
        this.adminname = adminname;
        this.adminpwd = adminpwd;
    }

    @Override
    public String toString() {
        return "admin{" +
                "adminId='" + adminId + '\'' +
                ", adminname='" + adminname + '\'' +
                ", adminpwd='" + adminpwd + '\'' +
                '}';
    }
}
