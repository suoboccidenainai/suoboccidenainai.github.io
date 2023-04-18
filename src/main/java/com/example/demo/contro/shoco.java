package com.example.demo.contro;

import com.example.demo.pojo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.ser.*;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/shop")
public class shoco {

    @Autowired
    private shoser shoser;

    @Autowired
    private useser useser;

    @Autowired
    private plaser plaser;


    @PostMapping("/pay")
    public resu pay(@RequestBody shop shop){
        log.info("支付");
        int userid = shop.getUserid();
        user u = useser.searchuser(userid);
        if(u.getMoney() >= shop.getMoney()){
            u.setMoney(u.getMoney() - shop.getMoney());
            useser.change(u);
            shoser.alter(shop);
            return resu.success("支付成功");
        }
        return resu.error("支付失败,余额不足");
    }

    @PostMapping("/add")
    public resu add(@RequestBody mes mes) {
        log.info("添加订单");
        Integer userid= mes.getUser().getId();
        Boolean num = shoser.searchuser(userid);
        if(num == null){

        }else if(num){
            return resu.error("用户存在订单");
        }
        place place =plaser.searchplace(mes.getNum());
        int placeid = place.getId();
        Date sl = place.getStartline();
        Date date = new Date();
        Date dl = place.getDeadline();
        if(sl.getTime() < date.getTime() &&  date.getTime() < dl.getTime()) {
            int money = place.getMoney();
            shoser.add(new shop(null, money,userid, placeid,null,true));
            return resu.success("创建成功");
        }
        return resu.error("当前不可创建该订单");
    }

    @PostMapping("/change")
    public resu change(@RequestBody shop shop){
        try {
            boolean r1 = shop.getUserid() != null && shop.getUserid() >= 0;
            boolean r2 = shop.getPlaceid() != null && shop.getPlaceid() >= 0;
            boolean r3 = shop.getMoney() != null && shop.getMoney() >= 0;
            if(!r1 || !r2 || !r3 ){
                return resu.error("数据输入有误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resu.error("数据输入有误");
        }

        log.info("修改");
        shoser.change(shop);
        return resu.success("修改成功");
    }


    @Transient
    @DeleteMapping("/delete/{id}")
    public resu delete(@PathVariable Integer id){
        log.info("删除订单");
        shoser.delete(id);
        return resu.success("订单已删除");
    }

    @GetMapping("/list")
    public resu list(){
        log.info("ai");
        List<shop> shoplist =shoser.list();
        return resu.success(shoplist);
    }

    @PostMapping("/show")
    public resu show(@RequestBody user user){
        shop u = shoser.show(user.getId());
        if(u != null){
            return resu.success(u);
        }
        return resu.success("当前没有订单");
    }

    @GetMapping("/superfind")
    public resu superfind(String s){
        List<shop> shops = shoser.superfind(s);
        return resu.success(shops);
    }

    @GetMapping("/payed")
    public resu payed(user user){
        List<shop> shops = shoser.payed(user.getId());
        log.info("",user);
        return resu.success(shops);
    }

    @PostMapping("/manageadd")
    public resu manageradd(@RequestBody shop shop){
        try {
            boolean r1 = shop.getUserid() != null && shop.getUserid() >= 0;
            boolean r2 = shop.getPlaceid() != null && shop.getPlaceid() >= 0;
            boolean r3 = shop.getMoney() != null && shop.getMoney() >= 0;
            boolean r4 = shop.getForm()==null;
            if(!r1 || !r2 || !r3 || r4){
                return resu.error("数据输入有误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resu.error("数据输入有误");
        }

        Integer userid= shop.getUserid();
        Boolean num = shoser.searchuser(userid);
        if(num == null){

        }else if(num && shop.getForm()){
            return resu.error("当前用户有未支付订单");
        }
        place place =plaser.searchplace(shop.getPlaceid());
        if(place == null){
            return resu.error("景点不存在");
        }

        if(shop.getForm()){
            Date sl = place.getStartline();
            Date date = new Date();
            Date dl = place.getDeadline();
            if(sl.getTime() < date.getTime() &&  date.getTime() < dl.getTime()) {
                return resu.error("当前不可创建该订单");
            }
        }
        int money = place.getMoney();
        shoser.add(new shop(null, money,userid,place.getId(),null,shop.getForm()));
        return resu.success("创建成功");

    }

}
