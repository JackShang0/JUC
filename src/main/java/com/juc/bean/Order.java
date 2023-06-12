package com.juc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @description
 * @author: shangqj
 * @date: 2023/3/13
 * @version: 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id ;
    private int price;
}


