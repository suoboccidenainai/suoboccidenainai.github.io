package com.example.demo.ser;

import com.example.demo.pojo.page;
import com.example.demo.pojo.place;

import java.util.List;

public interface plaser {
    List<place> list();

    void delete(Integer id);

    <T>void add(T t);

    <T>void change(T t);

    place searchplace(Integer id);

    page page(Integer s, Integer p);

    place find(place place);

    List<place> superfind(String s);
}
