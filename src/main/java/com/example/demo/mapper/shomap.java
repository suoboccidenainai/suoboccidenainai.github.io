package com.example.demo.mapper;


import com.example.demo.pojo.shop;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;



@Mapper
public interface shomap {

    @Select("select * from ject.shop where userid = #{userid} and form = true")
    shop search(Integer userid);

    @Select("select * from ject.shop where placeid = #{placeid}")
    Boolean searchplace(Integer placeid);

    @Select("select * from ject.shop where userid = #{userid} and form = true")
    Boolean searchuser(Integer userid);

    @Delete("delete from ject.shop where id = #{id}")
    void delete(Integer id);

    @Select("select * from ject.shop")
    List<shop> list();

    @Insert("insert into ject.shop(userid,placeid,money,createtime,form) values(#{userid},#{placeid},#{money},now(),true)")
    void add(shop shop);

    void change(shop shop);

    @Select("select * from shop where userid = #{userid} and form = 1")
    shop show(Integer userid);

    @Update("update shop set form = false where id = #{id}")
    void alter(shop shop);

    @Delete("delete from shop where userid = #{userid}")
    void userdelete(Integer userid);


    @Delete("delete from shop where placeid = #{placeid}")
    void placedelete(Integer placeid);

    @Select("select * from shop where userid = #{userid} and form = false")
    List<shop> payed(Integer userid);

    @Select("select * from shop where instr(userid,#{s}) or instr(placeid,#{s})")
    List<shop> superfind(String s);
}
