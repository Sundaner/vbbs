package com.htw.vbbs.douban;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class USBox implements Serializable {
    private String date;
    private ArrayList<UsSubjects> subjects;
}