package com.example.demo.contro;

import com.example.demo.pojo.place;
import com.example.demo.pojo.resu;
import com.example.demo.ser.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/place")
public class placo {

    @Autowired
    private plaser plaser;

    @Autowired
    private shoser shoser;

    @Autowired
    private colser colser;

    @GetMapping("/plalist")
    public resu list() {
        log.info("景点列表");
        List<place> placelist = plaser.list();
        return resu.success(placelist);
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/{id}")
    public resu delete(@PathVariable Integer id){
        log.info("删除:{}", id);
        place p = plaser.searchplace(id);
        if (p != null) {
            if(id >= 1 && id<= 8){
                return resu.error("首页景点不可删除");
            }
            plaser.delete(id);
            shoser.placedelete(id);
            colser.placedelete(id);
            return resu.success("删除成功");
        }
        return resu.error("不存在该景点,无法删除");
    }

    @PostMapping("/add")
    public resu add(@RequestBody place place) {
        log.info("添加:{}", place);
        Integer money = place.getMoney();
        Date sl = place.getStartline();
        Date dl = place.getDeadline();
        boolean b1 = money != null && money >= 0;
        boolean b2 = place.getName().matches("\\S{1,6}");
        boolean b3 = place.getResource().matches("\\S{1,100}");
        boolean b4 = place.getStartline() != null && place.getDeadline() != null;
        boolean b5 = sl.getTime() < dl.getTime();
        if (b1 && b2 && b3 && b4 && b5) {
            place b =plaser.find(place);
            //查重
            if(b==null) {
                plaser.add(place);
                return resu.success("添加成功");
            }
            return resu.error("景点信息重复，无法添加");
        }
        return resu.error("景点信息不正确，无法添加");
    }

    @PostMapping("/changepl")
    public resu change(@RequestBody place place){
        log.info("修改", place);
        try {
            boolean r1 = place.getDeadline() ==null;
            boolean r2 = place.getStartline() ==null;
            boolean r3 = place.getMoney() != null && place.getMoney() >= 0;
            if(r1 || r2 || r3 ){
                return resu.error("数据输入有误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return resu.error("数据输入有误");
        }

        boolean r1 = place.getName().matches("\\S{1,6}");
        boolean r2 = place.getResource().matches("\\S{1,100}");
        Date d1 = place.getStartline();
        Date d2 = place.getDeadline();
        boolean r3 = d2.getTime() > d1.getTime();
        if (r1 && r2 && r3) {
            plaser.change(place);
            return resu.success("修改成功");
        }
        return resu.error("信息有误，不能修改");
    }

    @GetMapping("/page")
    public resu page(@RequestParam(defaultValue = "1") Integer s, @RequestParam(defaultValue = "10") Integer p){
        log.info("分页",s);
        plaser.page(s,p);
        return resu.success();
    }

    @GetMapping("/superfind")
    public resu superfind(String s){
        List<place> places = plaser.superfind(s);
        return resu.success(places);
    }

    @GetMapping("/show")
    public resu show(Integer id){
        place p = plaser.searchplace(id);
        return resu.success(p);
    }



}