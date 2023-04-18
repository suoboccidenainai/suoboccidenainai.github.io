package com.example.demo.ser.impl;

import com.example.demo.pojo.user;
import com.example.demo.mapper.*;
import com.example.demo.ser.useser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class useim implements useser {
    @Autowired
    private usemap usemap;


    @Override
    public List<user> list() {
        return usemap.list();
    }

    @Override
    public user login(user user) {
        return usemap.login(user);
    }

    @Override
    public void delete(Integer id) {
        usemap.delete(id);
    }

    @Override
    public  void change(user user) {
        usemap.change(user);
    }



    @Override
    public  void add(user user) {
        usemap.add(user);
    }

    @Override
    public user searchuser(Integer num) {
        return usemap.searchuser(num);
    }

    @Override
    public user find(String account) {
        return usemap.find(account);
    }

    @Override
    public List<user> superfind(String s) {
        return usemap.superfind(s);
    }


}
