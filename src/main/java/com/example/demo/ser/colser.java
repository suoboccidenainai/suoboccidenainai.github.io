package com.example.demo.ser;


import com.example.demo.pojo.collect;
import com.example.demo.pojo.place;

import java.util.List;

public interface colser {
    List<place> list(Integer userid);

    void add(Integer placeid,Integer id);

    Boolean find(Integer placeid, Integer id);

    void delete(Integer num, Integer userid);

    void placedelete(Integer id);

    void userdelete(Integer id);
}
