package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
//景点
public class place {

    private Integer id;
    private String name;
    private String resource;//描述
    private Integer money;
    private Date startline;
    private Date deadline;
    private boolean form;
    private String image;


}
