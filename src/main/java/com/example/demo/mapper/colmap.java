package com.example.demo.mapper;

import com.example.demo.pojo.collect;
import com.example.demo.pojo.place;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface colmap {

    @Select("select *  from place,collect where place.id = collect.placeid and collect.userid =#{userid}")
    List<place> list(Integer userid);

    @Insert("insert into collect (userid, placeid) VALUES (#{userid},#{placeid})")
    void add(Integer placeid, Integer userid);

    @Select("select * from collect where placeid = #{placeid} and userid = #{userid}")
    Boolean find(Integer placeid, Integer userid);

    @Delete("delete from collect where placeid =#{placeid} and userid = #{userid}")
    void delete(Integer placeid, Integer userid);

    @Delete("delete from collect where placeid = #{placeid}")
    void placedelete(Integer placeid);

    @Delete("delete from collect where userid = #{userid}")
    void userdelete(Integer userid);
}
