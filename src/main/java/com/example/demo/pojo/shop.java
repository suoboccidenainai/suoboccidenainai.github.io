package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//订单
public class shop{
    private static final long serialVersionUID = 1L;
    private Integer id;//订单号
    private Integer money;//名称
    private Integer userid;//用户
    private Integer placeid;//景点
    private Date createtime;//创建日期
    private Boolean form;//订单状态
}
