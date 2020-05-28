package com.htw.vbbs.redis;

public class MusicKey extends BasePrefix {

    public static final int TOKEN_EXPIRE = -1;

    private MusicKey(int expireSeconds, String prefix) {
        super(expireSeconds,prefix);
    }
    public static MusicKey rank = new MusicKey(TOKEN_EXPIRE, "rank");
    public static MusicKey rankDetails = new MusicKey(TOKEN_EXPIRE, "rankDetails");
}
