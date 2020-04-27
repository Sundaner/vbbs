package com.htw.vbbs.douban;

import lombok.Data;
import java.io.Serializable;
import java.util.ArrayList;

@Data
public class DoubanMovie implements Serializable {
    private int count;
    private int start;
    private int total;
    private ArrayList<Subjects> subjects;
}
