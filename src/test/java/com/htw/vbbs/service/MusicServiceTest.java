package com.htw.vbbs.service;

import com.google.common.collect.Maps;
import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import com.htw.vbbs.util.HttpUtil;
import com.htw.vbbs.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MusicServiceTest {
    public static final String ENCODE = "UTF-8";
    public static final String GET = "GET";

    @Autowired
    private MusicService musicService;

    @Test
    public void testOkhttp(){
        String url = "https://api.apiopen.top/searchMusic";
        Map<String, String> param = Maps.newHashMap();
        param.put("name", "不要说话");
        String ok = OkHttpUtil.get(url, param);
        System.out.println(ok);
    }

    @Test
    public void testHttpUtil(){
        String url = "https://api.apiopen.top/searchMusic";
        Map<String, String> param = Maps.newHashMap();
        param.put("name", "不要说话");
        String ok = HttpUtil.httpRequst(url, param, ENCODE, GET);
        System.out.println(ok);
    }

    @Test
    public void testRank(){
        List<Rank> list = musicService.getRank();
        System.out.println(list);
    }

    @Test
    public void testDetail(){
        String type = "2";
        long id = 672865438;
        MusicDetail detail = musicService.getMusicDetail(type, id);
        System.out.println(detail);
    }
}
