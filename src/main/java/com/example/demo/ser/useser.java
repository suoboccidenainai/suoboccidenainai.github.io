package com.example.demo.ser;

import com.example.demo.pojo.user;
import java.util.List;


public interface useser{

    //user表单
    List<user> list();
    //登录
    user login(user user);
    //删
    void delete(Integer id);

    //改
    void change(user user);

    //增
    void add(user user);

    //查
    user searchuser(Integer num);

    user find(String account);

    List<user> superfind(String s);
}
