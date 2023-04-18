package com.example.demo.com;

import com.example.demo.pojo.place;
import com.example.demo.pojo.user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class baseset {
    private place placedata;
    private user userdata;
}

