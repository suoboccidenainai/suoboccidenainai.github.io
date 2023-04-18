package com.example.demo.mapper;

import com.example.demo.pojo.user;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface usemap {

    void change(user user);


    List<user> list();

    @Select("select * from ject.user where password = #{password} and account = #{account}")
    user login(user user);

    @Insert("insert into ject.user(username, account, password) VALUES (#{username},#{account},#{password})")
    void add(user user);

    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    @Select("select * from user where id = #{id}")
    user searchuser(Integer id);

    @Select("select * from user where account = #{account}")
    user find(String account);

    @Select("select * from user where instr(username,#{s}) or instr(account,#{s}) or instr(password,#{s})")
    List<user> superfind(String s);
}
