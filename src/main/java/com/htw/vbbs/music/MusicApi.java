package com.htw.vbbs.music;

public enum MusicApi{
    TANK("/musicRankings"),
    RANKDETAIL("/musicRankingsDetails"),
    DETAIL("/musicDetails");

    private String api;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    MusicApi(String api) {
        this.api = api;
    }
}
