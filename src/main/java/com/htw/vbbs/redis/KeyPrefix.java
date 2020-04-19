package com.htw.vbbs.redis;

public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
