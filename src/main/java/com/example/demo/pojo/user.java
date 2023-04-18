package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    private static final long serialVersionUID = 1L;
    private String username;
    private String account;
    private String password;
    private Integer money;
    private Integer id;
    private String strin;
}
