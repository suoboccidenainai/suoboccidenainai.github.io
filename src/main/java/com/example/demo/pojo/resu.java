package com.example.demo.pojo;
import com.example.demo.com.R;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
//通用返回结果类
public class resu {
    private Integer code;
    private String msg;
    private Object data;
    public static resu success(){
        resu r = new resu();
        r.msg = "succ";
        r.code = 1;
        return r;
    }

    public static resu success(Object o){
        return new resu(1,"succ",o);
    }

    public static resu error(){
        resu r = new resu();
        r.msg = "msg";
        r.code = 0;
        return r;
    }

    public static resu error(Object O){
        return new resu(0, error().msg, O);
    }


}