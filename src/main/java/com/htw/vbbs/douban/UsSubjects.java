package com.htw.vbbs.douban;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsSubjects implements Serializable {
    private int box;
    private int rank;
    private OneSubject subject;
}