package com.example.demo.contro;

import com.example.demo.pojo.resu;
import com.example.demo.pojo.user;
import com.example.demo.utils.jwtutil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.ser.useser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.com.*;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/login")
public class logco {

    private final int loginnum = 0;

    @Autowired
    private useser useser;

    @PostMapping("/l1")
    public resu login(@RequestBody user user){
        log.info("登录:{}",user);
        user user1 = useser.login(user);

        if (user1 != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("account", user1.getAccount());
            claims.put("password", user1.getPassword());
            claims.put("id",user1.getId());
            String jwt = jwtutil.tee(claims);
            return resu.success(jwt);
        }
        return resu.error("登陆失败");
    }

    @PostMapping("/l2")
    public resu login1( @RequestBody user user){
        if(user.getAccount().equals("123456789") && user.getPassword().equals("123456789")){
            Map<String, Object> claims = new HashMap<>();
            claims.put("account", user.getAccount());
            claims.put("password", user.getPassword());
            String jwt = jwtutil.tee(claims);
            return resu.success(jwt);
        }
        return resu.error("登陆失败");
    }


    @GetMapping("/exit")
    public resu exit(){
        return resu.success("login out");
    }

}
