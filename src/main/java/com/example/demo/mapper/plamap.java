package com.example.demo.mapper;

import com.example.demo.pojo.place;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface plamap {

        List<place> list();

        @Delete("delete from place where id = #{id}")
        void delete(Integer id);

        @Insert("insert into ject.place(name, resource, startline, deadline, money) values(#{name},#{resource},#{startline},#{deadline},#{money})")
        void add(place place);

        void change(place place);

        @Select("select * from ject.place where id = #{id}")
        place searchplace(Integer id);

        @Select("select * from place LIMIT #{start},#{page}")
        List<place>  page(Integer start,Integer page);


        place find(place place);

        @Select("select * from place where instr(name,#{s}) or instr(resource,#{s})")
        List<place> superfind(String s);
}
