package com.example.demo.contro;

import com.example.demo.pojo.*;
import com.example.demo.ser.*;
import com.example.demo.pojo.resu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/col")
public class colco {

    @Autowired
    private colser colser;

    @GetMapping("/list")
    public resu list(Integer userid){
        List<place> list = colser.list(userid);
        return resu.success(list);
    }

    @PostMapping("/add")
    public resu add(@RequestBody mes mes){
        Integer placeid = mes.getNum();
        user user = mes.getUser();
        Boolean r = colser.find(placeid,user.getId());
        if(r == null){

        }else if(r){
            return resu.error("已收藏该景点");
        }
        colser.add(placeid,user.getId());
        return resu.success("收藏成功");
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/delete")
    public resu delete(@RequestBody mes mes){
        Integer num = mes.getNum();
        Integer userid = mes.getUser().getId();
        colser.delete(num,userid);
        return resu.success("删除成功");
    }
}

//    @PostMapping("/c1")
//    public resu list(@RequestBody user user){
//        log.info("查找",user);
//        user use = useser.searchuser(user.getId());
//        String strin = use.getStrin();
//        if(strin == null){
//            return resu.success("用户未添加收藏景点");
//        }
//        char [] x =strin.toCharArray();
//        StringBuilder b =new StringBuilder();
//        for(int i = 0; i < x.length;i++){
//            char q = x[i];
//            if(Character.isDigit(q)){
//                b.append(q);
//            }else if (Character.isWhitespace(q)){
//                b.append(" ");
//            }
//        }
//        String [] p =b.toString().split(" ");
//        return  resu.success(p);
//    }

//    @PostMapping ("/c2")
//    public resu add(@RequestBody mes mes){
//        Integer num = mes.getNum();
//        Integer id = mes.getUser().getId();
//        user use = useser.searchuser(id);
//        if(plaser.searchplace(num) == null){
//            return resu.error("不存在该景点");
//        }else if (useser.searchuser(id) == null){
//            return  resu.error("不存在该用户");
//        }
//
//        String s1[] = use.getStrin().split(" ");
//        if(binarySearch(s1,num) == -1){
//            String s = use.getStrin();
//            StringBuilder b = new StringBuilder();
//            b.append(s + " " + num);
//            String strin= b.toString();
//            colser.add(id,strin);
//            return resu.success("添加收藏成功");
//        }
//        return resu.error("景点添加重复");
//    }


//    public int binarySearch(String[] array, int des) {
//        int low = 0, high = array.length - 1;
//        while(low <= high) {
//            int mid = (low + high) / 2;
//            if(des ==  Integer.parseInt(array[mid])) {
//                return mid;
//            } else if(des > Integer.parseInt(array[mid])) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
//        return -1;
//    }

