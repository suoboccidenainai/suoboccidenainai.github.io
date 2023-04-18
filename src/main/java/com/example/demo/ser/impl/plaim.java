package com.example.demo.ser.impl;

import com.example.demo.pojo.page;
import com.example.demo.pojo.place;
import com.example.demo.mapper.*;
import com.example.demo.ser.plaser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class plaim implements plaser {

    @Autowired
    private plamap plamap;
    //查表单
    @Override
    public List<place> list() {
        return plamap.list();
    }
    //删
    @Override
    public void delete(Integer id) {
        plamap.delete(id);
    }
    //增
    @Override
    public <T>void add(T t) {
        plamap.add((place) t);
    }
    //改
    @Override
    public <T> void change(T t) {
        plamap.change((place) t);
    }

    //查单个景点
    @Override
    public place searchplace(Integer id) {
        return plamap.searchplace(id);
    }

    @Override
    public page page(Integer s, Integer p) {

        Integer num = plamap.list().size();
        Integer start =(s-1) * p;
        List<place> list = plamap.page(s,p);
        page page = new page(num,list);
        return null;
    }

    @Override
    public place find(place place) {
        return plamap.find(place);
    }

    @Override
    public List<place> superfind(String s) {
        return plamap.superfind(s);
    }
}
