package com.example.demo.ser.impl;

import com.example.demo.mapper.colmap;
import com.example.demo.pojo.collect;
import com.example.demo.pojo.place;
import com.example.demo.ser.colser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class colim implements colser {


    @Autowired
    private colmap colmap;
    @Override
    public List<place> list(Integer userid) {
        return colmap.list(userid);
    }

    @Override
    public void add(Integer placeid,Integer id) {
        colmap.add(placeid,id);
    }



    @Override
    public void delete(Integer num, Integer userid) {
        colmap.delete(num,userid);
    }

    @Override
    public void placedelete(Integer placeid) {
        colmap.placedelete(placeid);
    }

    @Override
    public void userdelete(Integer id) {
        colmap.userdelete(id);
    }

    @Override
    public Boolean find(Integer placeid, Integer id) {
        return colmap.find(placeid,id);
    }
}
