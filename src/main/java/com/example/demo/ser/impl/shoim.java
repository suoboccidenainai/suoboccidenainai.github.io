package com.example.demo.ser.impl;

import com.example.demo.mapper.shomap;
import com.example.demo.pojo.shop;
import com.example.demo.ser.shoser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class shoim implements shoser {

    @Autowired
    private shomap shomap;

    @Override
    public shop search(int id) {
        return shomap.search(id);
    }

    @Override
    public void delete(Integer num) {
        shomap.delete(num);
    }

    @Override
    public List<shop> list() {
        return shomap.list();
    }

    @Override
    public void add(shop shop) {
        shomap.add(shop);
    }

    @Override
    public Boolean searchplace(Integer id) {
        return shomap.searchplace(id);
    }

    @Override
    public Boolean searchuser(Integer userid) {
        return shomap.searchuser(userid);
    }

    @Override
    public void change(shop shop) {
        shomap.change(shop);
    }


    @Override
    public shop show(Integer userid) {
        return shomap.show(userid);
    }

    @Override
    public void alter(shop shop) {
        shomap.alter(shop);
    }

    @Override
    public void placedelete(Integer placeid) {
        shomap.placedelete(placeid);
    }

    @Override
    public List<shop> payed(Integer id) {
        return shomap.payed(id);
    }

    @Override
    public void userdelete(Integer userid) {
        shomap.userdelete(userid);
    }

    @Override
    public List<shop> superfind(String s) {
        return shomap.superfind(s);
    }


}
