package com.example.demo.contro;

import com.example.demo.pojo.mes;
import com.example.demo.pojo.resu;
import com.example.demo.pojo.shop;
import com.example.demo.pojo.user;
import com.example.demo.ser.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.ser.shoser;
import java.beans.Transient;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class useco {

    @Autowired
    private useser useser;

    @Autowired
    private shoser shoser;

    @Autowired
    private colser colser;

    @GetMapping("/userlist")
    public <T>resu list() {
        log.info("ai");
        List<user> uselist =useser.list();
        return resu.success(uselist);
    }

    @PostMapping("/user1")
    public resu list(@RequestBody user u){
        log.info("表单");
        user user =useser.searchuser(u.getId());
        if(user !=null){
            return resu.success(user);
        }
        return resu.error("没有找到该用户");
    }

    @Transient
    @DeleteMapping("/{id}")
    public resu delete(@PathVariable Integer id){
        log.info("删除:{}",id);;
        shop result = shoser.search(id);
        if(result == null){
            useser.delete(id);
            shoser.userdelete(id);
            colser.userdelete(id);
            return resu.success("删除成功");
        }else{
            return resu.error("该用户存在订单，不可删除");
        }
    }

    @PostMapping("/userchange")
    public resu change(@RequestBody user user){
        log.info("修改",user);
        Integer money = user.getMoney();
        boolean r1 = user.getAccount().matches("^1[3456789]\\d{9}$");
        boolean r2 = user.getPassword().matches("^[a-zA-Z]\\w{5,17}$");
        boolean r3 = money != null && money >= 0;
        if(r1 && r2 && r3){
            useser.change(user);
            return resu.success("修改成功");
        }
       return resu.error("");
    }

    @PostMapping("/add")
    public resu add(@RequestBody user user){
        log.info("增加",user);
        user user1 =useser.find(user.getAccount());
        if(user1 != null ){
            return resu.error("此账号已被注册");
        }
        boolean b1= user.getPassword().matches("^[a-zA-Z]\\w{5,17}$");
        boolean b2= user.getUsername().matches("\\S{1,6}");
        if(!b1 || !b2){
            return resu.error("信息格式有误");
        }
        useser.add(user);
        return resu.success("增加成功");
    }

    @PostMapping("/earn")
    public resu money(@RequestBody mes mes){
        log.info("充值",mes);
        Integer u1 = mes.getUser().getId();
        int num =mes.getNum();
        if(u1 ==null || num <= 0){
            return resu.error("充值失败,信息有误");
        }
        user user = useser.searchuser(u1);
        int money = user.getMoney();
        user.setMoney(money + num);
        useser.change(user);
        return resu.success("充值成功");
    }

    @PostMapping("/forget")
    public resu forget(@RequestBody user user){
        String account = user.getAccount();
        user user1 = useser.find(account);
        if(user1 == null){
            return resu.error("未找到该用户");
        }else{
            user1.setPassword(user.getPassword());
            useser.change(user1);
            return resu.success("修改密码成功");
        }
    }

    @GetMapping("/superfind")
    public resu superfind(String s){
        List<user> users = useser.superfind(s);
        return resu.success(users);
    }

}
