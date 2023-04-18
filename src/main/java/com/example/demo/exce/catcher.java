package com.example.demo.exce;


import com.example.demo.pojo.resu;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class catcher{

    @ExceptionHandler(Exception.class)
    public resu ex(Exception e){
        e.printStackTrace();
        return resu.error("操作失败");
    }
}
