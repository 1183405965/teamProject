package cn.bookstore.pojo;
//用户类
public class User {
    // 对应数据库数据
    private String uid;          //主键
    private String loginname;    //登录名
    private String loginpass;    //登录密码
    private String email;        //邮箱
    private boolean status;       //状态，tinyint
    private String activationCode;//激活码  --激活码待定，先不管
    //user/pwd.jsp里的修改密码
    private String newpass;        //修改后的新密码
    //user/regist.jsp里的注册表单
    private String reloginpass;    //确认密码
    private String verifyCode;     //验证码
}
