package com.example.demo.ser;

import com.example.demo.pojo.shop;

import java.util.List;

public interface shoser {


    shop search(int id);

    void delete(Integer num);

    List<shop> list();

    void add(shop shop);

    Boolean searchplace(Integer id);

    Boolean searchuser(Integer userid);

    void change(shop shop);


    shop show(Integer id);

    void alter(shop shop);

    void placedelete(Integer id);

    List<shop> payed(Integer id);

    void userdelete(Integer id);

    List<shop> superfind(String s);
}
