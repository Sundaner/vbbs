package com.htw.vbbs.douban;

import lombok.Data;
import java.io.Serializable;

@Data
public class Rating implements Serializable {
    private int max;
    private double average;
    private String stars;
    private int min;
}
