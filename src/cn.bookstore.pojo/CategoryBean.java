package cn.bookstore.pojo;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Map;
import java.util.UUID;

public class CategoryBean {
/*两个用处：1,生成不重复的id   2,封装一个map放回为Category对象，也就在子分类*/
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
}
    public static String uuid1() {

        String sql = " select count(*) FROM t_category WHERE pid is null ";

        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /*
     * 把一个Map中的数据映射到Category中,单个map
     */
    public static Category toCategory(Map<String,Object> map) {
        /*
         * map {cid:xx, cname:xx, pid:xx, desc:xx, orderBy:xx}
         * Category{cid:xx, cname:xx, parent:(cid=pid), desc:xx}
         */
        Category category =CategoryBean.toBean(map, Category.class);
        String pid = (String)map.get("pid");// 如果是一级分类，那么pid是null
        if(pid != null) {//如果父分类ID不为空，吧一个map变成parent
            /*
             * 使用一个父分类对象来拦截pid
             */
            Category parent = new Category();
            parent.setCid(pid);
            category.setParent(parent);
        }
        return category;
    }
/* 1. 通过参数clazz创建实例   2. 使用BeanUtils.populate把map的数据封闭到bean中*/
    public static  <T> T toBean(Map<String, Object> map, Class<T> t) {  //Category
        try {
            T bean = t.getConstructor().newInstance();
            ConvertUtils.register(new DateConverter(),java.util.Date.class);
            BeanUtils.populate(bean,map);   /*存疑，不知道干嘛的，源码没看懂*/
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   /* public static Object toBean(Map<String, String[]> parameterMap, Class<Category> categoryClass) {
        try {
            Object bean = categoryClass.getConstructor().newInstance();
            ConvertUtils.register(new DateConverter(),java.util.Date.class);
            BeanUtils.populate(bean,parameterMap);   *//*存疑，不知道干嘛的，源码没看懂*//*
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
