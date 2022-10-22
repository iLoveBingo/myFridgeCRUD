package com.ilovebingo.myfridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    private int id;
    private String name;
    private int weight;
    private Date insertDate;
    private Date expiringDate;
}
