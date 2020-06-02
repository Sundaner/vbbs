package com.htw.vbbs.service;

import com.alibaba.fastjson.JSON;
import com.htw.vbbs.music.MusicApi;
import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import com.htw.vbbs.music.response.DetailResp;
import com.htw.vbbs.music.response.RankResp;
import com.htw.vbbs.redis.MusicKey;
import com.htw.vbbs.redis.RedisService;
import com.htw.vbbs.util.OkHttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicService {

    @Autowired
    private OkHttpUtil okHttpUtil;
    @Autowired
    private RedisService redisService;

    public static String getaway = "https://api.apiopen.top";

    public List<Rank> getRank(){
        Map<String, String> query = new HashMap<>();
        String url = getaway + MusicApi.TANK.getApi();
        String json = OkHttpUtil.get(url, query);
        RankResp resp = JSON.parseObject(json, RankResp.class);
        if(resp.getCode() != 200 || resp.getResult() == null){
            json = redisService.get(MusicKey.rank, "", String.class);
            resp = JSON.parseObject(json, RankResp.class);
        }
        return resp.getResult();
    }


    public MusicDetail getMusicDetail(String type, String id){
        Map<String, String> query = new HashMap<>();
        query.put("type", type);
        String url = getaway + MusicApi.RANKDETAIL.getApi();
        String json = OkHttpUtil.get(url, query);
        DetailResp resp = JSON.parseObject(json, DetailResp.class);
        if(resp.getCode() != 200 || resp.getResult() == null){
            json = redisService.get(MusicKey.rankDetails, type, String.class);
            resp = JSON.parseObject(json, DetailResp.class);
        }
        for(MusicDetail  detail : resp.getResult()){
            if(id.equals(detail.getSong_id())){
                String lyric = "暂无歌词";
                if(StringUtils.isNotEmpty(detail.getLrclink())){
                    lyric = okHttpUtil.getLrcTXT(detail.getLrclink());
                }
                detail.setLyric(lyric);
                return detail;
            }
        }
        return null;
    }
}


