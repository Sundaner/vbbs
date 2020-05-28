package com.htw.vbbs.service;

import com.htw.vbbs.redis.MusicKey;
import com.htw.vbbs.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testGet(){
        String json = "{\n" +
                "    \"code\": 200,\n" +
                "    \"message\": \"成功!\",\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_734232335ef76f5a05179797875817f3.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5bfe4e9aa7496_218.png\",\n" +
                "            \"color\": \"0xDC5900\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/c83d70cf3bc79f3d98ca8e36b8a1cd11728b2988.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 2,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"672865438\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"舞蹈女神诺涵\",\n" +
                "                    \"album_id\": \"672865436\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/b733a1a9fc0f63c7015be29b7b840b66/672866107/672866107.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"桥边姑娘\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/b733a1a9fc0f63c7015be29b7b840b66/672866107/672866107.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"桥边姑娘\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"675017674\",\n" +
                "                    \"rank_change\": \"1\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"宋小睿\",\n" +
                "                    \"album_id\": \"675017666\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"少年（童声版）\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"少年\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"265715650\",\n" +
                "                    \"rank_change\": \"1\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"周深\",\n" +
                "                    \"album_id\": \"265715651\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/0d359ec1be6f5365f92d4c83d3eeb022/603758238/603758238.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"大鱼\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/0d359ec1be6f5365f92d4c83d3eeb022/603758238/603758238.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"大鱼\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"242078437\",\n" +
                "                    \"rank_change\": \"-2\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"薛之谦\",\n" +
                "                    \"album_id\": \"241838068\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/49e6161afb13e3eda9d1cb4e304561a2/676820947/676820947.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"演员\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/49e6161afb13e3eda9d1cb4e304561a2/676820947/676820947.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"初学者\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xFBEFE6\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"热歌榜\",\n" +
                "            \"comment\": \"该榜单是根据千千音乐平台歌曲每周播放量自动生成的数据榜单，统计范围为千千音乐平台上的全部歌曲，每日更新一次\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1452f36a8dc430ccdb8f6e57be6df2ee.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/838ba61ea8d3fd1f1326c83c324e251f95ca5f8c.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_dea655f4be544132fb0b5899f063d82e.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5c3d586d234b4_292.png\",\n" +
                "            \"color\": \"0x5B9400\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/78310a55b319ebc4845c84eb8026cffc1e17169f.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 1,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"675017674\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"宋小睿\",\n" +
                "                    \"album_id\": \"675017666\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"少年（童声版）\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"少年\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"675217012\",\n" +
                "                    \"rank_change\": \"1\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-3\",\n" +
                "                    \"author\": \"A公馆\",\n" +
                "                    \"album_id\": \"675217010\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"拥抱春天\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"拥抱春天\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"675012544\",\n" +
                "                    \"rank_change\": \"1\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"佟丽娅,蔡徐坤\",\n" +
                "                    \"album_id\": \"675012542\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"山河无恙在我胸-蔡徐坤+佟丽娅\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"山河无恙在我胸\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"64\",\n" +
                "                    \"song_id\": \"675894480\",\n" +
                "                    \"rank_change\": \"-2\",\n" +
                "                    \"biaoshi\": \"perm-1\",\n" +
                "                    \"author\": \"陈奕迅,蔡依林\",\n" +
                "                    \"album_id\": \"675894478\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"Fight as ONE\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"Fight as ONE\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xEFF5E6\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"新歌榜\",\n" +
                "            \"comment\": \"该榜单是根据千千音乐平台歌曲每日播放量自动生成的数据榜单，统计范围为近期发行的歌曲，每日更新一次\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_9a4fbbbfa50203aaa9e69bf189c6a45b.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/e850352ac65c1038cb0f3cb0b0119313b07e894b.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_e58a5196bcd49d77d4a099b60b0bc03b.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5bfe4eacbcea8_225.png\",\n" +
                "            \"color\": \"0x21BFA6\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/738b4710b912c8fca95d9ecbfe039245d688210d.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 25,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"672526448\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"龙梅子\",\n" +
                "                    \"album_id\": \"672526446\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/28e4b596b16737fbaf54c600c19947a6/672527540/672527540.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"记忆里的雪\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/28e4b596b16737fbaf54c600c19947a6/672527540/672527540.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"记忆里的雪\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"674872503\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"大庆小芳\",\n" +
                "                    \"album_id\": \"674872498\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/45274cea2aa721455965686674cbbca9/674872531/674872531.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"听你\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/45274cea2aa721455965686674cbbca9/674872531/674872531.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"听你\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"674078277\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"乌兰图雅\",\n" +
                "                    \"album_id\": \"674078275\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/5b11a590fa45946e4e2f74b952a345af/674078794/674078794.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"共同战疫\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/5b11a590fa45946e4e2f74b952a345af/674078794/674078794.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"共同战疫\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"672908895\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"冷漠\",\n" +
                "                    \"album_id\": \"672908874\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/57c0ccc68f5b76aded1e2d70076a5052/674235834/674235834.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"有一种爱\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/57c0ccc68f5b76aded1e2d70076a5052/674235834/674235834.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"爱在琴澳（庆祝澳门回归20周年原创音乐大碟）\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xE9F9F6\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"网络歌曲榜\",\n" +
                "            \"comment\": \"实时展现千千音乐最热门网络歌曲排行\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_6b67628d46638bccdc6bd8c9854b759b.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/6c224f4a20a44623d567cd649a22720e0cf3d703.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_4de71fefad5f1fdb0a90b3c51b5a6f97.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5bfe4ed160c12_921.png\",\n" +
                "            \"color\": \"0x967456\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/f703738da97739121a5aed67fa198618367ae2bc.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 24,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"257535276\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"贾乃亮,贾云馨\",\n" +
                "                    \"album_id\": \"260368616\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/f1e4bb1d9eb1eaddde7d8647602fc24b/672906500/672906500.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"大王叫我来巡山\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/f1e4bb1d9eb1eaddde7d8647602fc24b/672906500/672906500.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"万万没想到 电影原声带\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"242375818\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"张碧晨\",\n" +
                "                    \"album_id\": \"583718765\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/e7e21cf66583ac2392c82852c5dee7e1/672614017/672614017.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"年轮\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/e7e21cf66583ac2392c82852c5dee7e1/672614017/672614017.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"《花千骨》OST\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"260375666\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"崔子格\",\n" +
                "                    \"album_id\": \"260375703\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/3fdeac31e0fa76a2bc87580bc1ad6b1c/610777743/610777743.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"可念不可说\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/3fdeac31e0fa76a2bc87580bc1ad6b1c/610777743/610777743.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"太子妃升职记 网剧原声带\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"246732955\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"林俊杰,金莎\",\n" +
                "                    \"album_id\": \"246732962\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/6e725d74079aa90476655effb1b7cbf3/611737404/611737404.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"被风吹过的夏天\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/6e725d74079aa90476655effb1b7cbf3/611737404/611737404.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"最佳前男友 电视原声\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xF5F1EE\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"影视金曲榜\",\n" +
                "            \"comment\": \"实时展现千千音乐最热门影视歌曲排行\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_2347fb14878de20a3b972a8f44a5c3a8.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/9f2f070828381f3052bae5afab014c086e06f011.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_ecd2c13c57fb27574b9e758e4f707cef.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5bfe4e5a1364a_423.png\",\n" +
                "            \"color\": \"0xD98E26\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/6f061d950a7b0208b85e57e760d9f2d3572cc825.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 22,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"276766\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"郑钧\",\n" +
                "                    \"album_id\": \"70313\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/ea5c9b6e9024b2bbee9cb392e3011523/557288577/557288577.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"灰姑娘\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/ea5c9b6e9024b2bbee9cb392e3011523/557288577/557288577.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"赤裸裸\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"963292\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"老狼\",\n" +
                "                    \"album_id\": \"73078\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/c42c1234d927d81d0d2b85cd567e8e29/613644590/613644590.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"恋恋风尘\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/c42c1234d927d81d0d2b85cd567e8e29/613644590/613644590.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"恋恋风尘\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"5919772\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"何炅\",\n" +
                "                    \"album_id\": \"106384\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/a899a4c610328a854959bfc48a23bd38/106384/106384.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"栀子花开\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/a899a4c610328a854959bfc48a23bd38/106384/106384.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"可以爱\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320\",\n" +
                "                    \"song_id\": \"682485\",\n" +
                "                    \"rank_change\": \"2\",\n" +
                "                    \"biaoshi\": \"vip,perm-1\",\n" +
                "                    \"author\": \"毛宁\",\n" +
                "                    \"album_id\": \"2449425\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/0001d77d7ac206c49cc1fe1485f91113/601759310/601759310.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"涛声依旧\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/0001d77d7ac206c49cc1fe1485f91113/601759310/601759310.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"百年经典8: 弯弯的月亮\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xFBF4EA\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"经典老歌榜\",\n" +
                "            \"comment\": \"实时展现千千音乐最热门经典老歌排行\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_e6b795613995b69b73861ca7e7732015.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/0bd162d9f2d3572cd909f4da8813632763d0c3c9.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pic_s210\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_ad19dd0cd653dbf9b2fca05b5ae6f87f.jpg\",\n" +
                "            \"bg_pic\": \"http://business0.qianqian.com/qianqian/file/5bfe4e726acbc_309.png\",\n" +
                "            \"color\": \"0x4A90E2\",\n" +
                "            \"pic_s444\": \"http://hiphotos.qianqian.com/ting/pic/item/8d5494eef01f3a291bf6bec89b25bc315c607cfd.jpg\",\n" +
                "            \"count\": 4,\n" +
                "            \"type\": 21,\n" +
                "            \"content\": [\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"590762992\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,perm-1\",\n" +
                "                    \"author\": \"Vitas\",\n" +
                "                    \"album_id\": \"590762988\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/4d475c81e4c562301e21c2d102165205/590761655/590761655.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"Симфония № 4（交响乐4号）\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/4d475c81e4c562301e21c2d102165205/590761655/590761655.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"20\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320\",\n" +
                "                    \"song_id\": \"65626244\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"perm-1\",\n" +
                "                    \"author\": \"弗雷德乐队\",\n" +
                "                    \"album_id\": \"65626242\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/88411609/88411609.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"因为爱情 (法语版)\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/88411609/88411609.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"致青春 (法语版)\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,224,128,320,flac\",\n" +
                "                    \"song_id\": \"664722524\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"Alan Walker,ADMA\",\n" +
                "                    \"album_id\": \"664722522\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/a906d837dec9442f81237e3dd204d8d9/664723682/664723682.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"On My Way (ADMA Bootleg)\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/a906d837dec9442f81237e3dd204d8d9/664723682/664723682.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"On My Way (ADMA Bootleg)\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"all_rate\": \"96,128,224,320,flac\",\n" +
                "                    \"song_id\": \"588688244\",\n" +
                "                    \"rank_change\": \"0\",\n" +
                "                    \"biaoshi\": \"lossless,vip,perm-1\",\n" +
                "                    \"author\": \"I WEAR* EXPERIMENT\",\n" +
                "                    \"album_id\": \"588688241\",\n" +
                "                    \"pic_small\": \"http://qukufile2.qianqian.com/data2/pic/3aa6422af908cf869262f9c75f3c3530/588688242/588688242.jpg@s_2,w_90,h_90\",\n" +
                "                    \"title\": \"Dogs\",\n" +
                "                    \"pic_big\": \"http://qukufile2.qianqian.com/data2/pic/3aa6422af908cf869262f9c75f3c3530/588688242/588688242.jpg@s_2,w_150,h_150\",\n" +
                "                    \"album_title\": \"Dogs\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"bg_color\": \"0xEDF4FC\",\n" +
                "            \"web_url\": \"\",\n" +
                "            \"name\": \"欧美金曲榜\",\n" +
                "            \"comment\": \"实时展现千千音乐最热门欧美歌曲排行\",\n" +
                "            \"pic_s192\": \"http://business.cdn.qianqian.com/qianqian/pic/bos_client_9362a5a55277e6fb271010a45bc99e17.jpg\",\n" +
                "            \"pic_s260\": \"http://hiphotos.qianqian.com/ting/pic/item/8b13632762d0f7035cb3feda0afa513d2697c5b7.jpg\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        redisService.set(MusicKey.rank, "", json);
    }

    @Test
    public void testD(){
        String json = "{\"code\":200,\"message\":\"成功!\",\"result\":[{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"lossless,perm-1\",\"artist_name\":\"宋小睿\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg\",\"all_rate\":\"96,224,128,320,flac\",\"song_source\":\"web\",\"song_id\":\"675017674\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_500,h_500\",\"rank\":\"1\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"labelname_北京欣然文化传媒有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"宋小睿\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"340642446\",\"artist_id\":\"674713815\",\"versions\":\"\",\"publishtime\":\"2020-03-24\",\"style\":\"\",\"album_id\":\"675017666\",\"album_no\":\"2\",\"resource_type_ext\":\"0\",\"del_status\":\"0\",\"hot\":\"133785\",\"toneid\":\"0\",\"title\":\"少年（童声版）\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"0\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/cc8492076ab0def9daf88daf9a9b3751/675022268/675022268.txt\",\"file_duration\":236,\"havehigh\":2,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"674713815\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/8b1aab6be81f10639c01c1401a20463c/675021896/675021896.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"340642446\",\"album_title\":\"少年\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"lossless,vip,perm-3\",\"artist_name\":\"A公馆\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"96,224,128,320,flac\",\"song_source\":\"web\",\"song_id\":\"675217012\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_500,h_500\",\"rank\":\"2\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"华宇世博音乐文化（北京）有限公司-普通代理\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"A公馆\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"209968568\",\"artist_id\":\"60722128\",\"versions\":\"\",\"publishtime\":\"2020-03-27\",\"style\":\"\",\"album_id\":\"675217010\",\"album_no\":\"1\",\"resource_type_ext\":\"2\",\"del_status\":\"0\",\"hot\":\"79082\",\"toneid\":\"0\",\"title\":\"拥抱春天\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"1\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/b5459ebbfdea9acecf2c462e9b70ce44/675236811/675236811.txt\",\"file_duration\":279,\"havehigh\":2,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"60722128\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/d24656e3b055acb05fe096f781920016/675907119/675907119.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"129|-1\\\",\\\"1\\\":\\\"-1|-1\\\"}\",\"has_mv\":1,\"copy_type\":\"1\",\"ting_uid\":\"209968568\",\"album_title\":\"拥抱春天\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"lossless,vip,perm-1\",\"artist_name\":\"佟丽娅,蔡徐坤\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_1000,h_1000\",\"all_rate\":\"96,224,128,320,flac\",\"song_source\":\"web\",\"song_id\":\"675012544\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_500,h_500\",\"rank\":\"3\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"永稻星（北京）文化娱乐有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"佟丽娅,蔡徐坤\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"9631389,239563102\",\"artist_id\":\"14715949\",\"versions\":\"\",\"publishtime\":\"2020-03-25\",\"style\":\"\",\"album_id\":\"675012542\",\"album_no\":\"1\",\"resource_type_ext\":\"2\",\"del_status\":\"0\",\"hot\":\"74470\",\"toneid\":\"0\",\"title\":\"山河无恙在我胸-蔡徐坤+佟丽娅\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"1\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/4d34b71f289827f459aadc951b172248/675012957/675012957.txt\",\"file_duration\":290,\"havehigh\":2,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_500,h_500\",\"all_artist_id\":\"14715949,267237378\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/bae766e5c670ca417cc433156efcb528/675012927/675012927.png@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"129|-1\\\",\\\"1\\\":\\\"-1|-1\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"9631389\",\"album_title\":\"山河无恙在我胸\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"perm-1\",\"artist_name\":\"陈奕迅,蔡依林\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675894480\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_500,h_500\",\"rank\":\"4\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"永稻星（北京）文化娱乐有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"陈奕迅,蔡依林\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"1077,1034\",\"artist_id\":\"87\",\"versions\":\"\",\"publishtime\":\"2020-04-03\",\"style\":\"\",\"album_id\":\"675894478\",\"album_no\":\"1\",\"resource_type_ext\":\"0\",\"del_status\":\"0\",\"hot\":\"26708\",\"toneid\":\"0\",\"title\":\"Fight as ONE\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"-2\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/6062cd0c1ac99981f8c8ccb8f6c1524e/675894483/675894483.txt\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"87,12\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/56ed3280e6ed46ec7bb49cb0ee19af5b/675894516/675894516.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"1077\",\"album_title\":\"Fight as ONE\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"perm-1\",\"artist_name\":\"韩红\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675916125\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_500,h_500\",\"rank\":\"5\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"奔跑怪物（北京）文化娱乐有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"韩红\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"1219\",\"artist_id\":\"362\",\"versions\":\"\",\"publishtime\":\"2020-04-08\",\"style\":\"\",\"album_id\":\"675916123\",\"album_no\":\"1\",\"resource_type_ext\":\"0\",\"del_status\":\"0\",\"hot\":\"9716\",\"toneid\":\"0\",\"title\":\"只要平凡\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"2\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/464b128cdcd633111f18b2b6c7a585a8/675916128/675916128.txt\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"362\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/47879efd15685bc1c101822ff1df4d09/675916298/675916298.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"1219\",\"album_title\":\"只要平凡\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"vip,perm-1\",\"artist_name\":\"李思光\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675216983\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_500,h_500\",\"rank\":\"6\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"华宇世博音乐文化（北京）有限公司-九天石代理\",\"has_mv_mobile\":0,\"is_new\":\"0\",\"author\":\"李思光\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"340657235\",\"artist_id\":\"675216980\",\"versions\":\"\",\"publishtime\":\"2020-03-27\",\"style\":\"\",\"album_id\":\"675216981\",\"album_no\":\"1\",\"resource_type_ext\":\"2\",\"del_status\":\"0\",\"hot\":\"0\",\"toneid\":\"0\",\"title\":\"稳住\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"0\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/8ec52d2659b1413ffcbfa5803adc1f5a/676261706/676261706.lrc\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"675216980\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/0903f0ca3fcb7b1bfd5d3c024ec40d34/676261704/676261704.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"129|-1\\\",\\\"1\\\":\\\"-1|-1\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"340657235\",\"album_title\":\"稳住\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"lossless,vip,perm-1\",\"artist_name\":\"苍耳乐队\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"96,128,224,320,flac\",\"song_source\":\"web\",\"song_id\":\"675340822\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_500,h_500\",\"rank\":\"7\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"华宇世博音乐文化（北京）有限公司-九天石代理\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"苍耳乐队\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"340623621\",\"artist_id\":\"673534857\",\"versions\":\"\",\"publishtime\":\"2020-03-30\",\"style\":\"\",\"album_id\":\"675340820\",\"album_no\":\"1\",\"resource_type_ext\":\"2\",\"del_status\":\"0\",\"hot\":\"18230\",\"toneid\":\"0\",\"title\":\"遇\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"2\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/8d0755413928555f53025d219c42ffe1/675341062/675341062.txt\",\"file_duration\":303,\"havehigh\":2,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"673534857\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/073bdc580730ff4d6d5fa40ace623825/675907121/675907121.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"129|-1\\\",\\\"1\\\":\\\"-1|-1\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"340623621\",\"album_title\":\"遇\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"perm-3\",\"artist_name\":\"蔡徐坤\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675916097\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_500,h_500\",\"rank\":\"8\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"上海圣臻文化发展有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"蔡徐坤\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"239563102\",\"artist_id\":\"267237378\",\"versions\":\"\",\"publishtime\":\"2020-04-09\",\"style\":\"\",\"album_id\":\"675916095\",\"album_no\":\"1\",\"resource_type_ext\":\"0\",\"del_status\":\"0\",\"hot\":\"6314\",\"toneid\":\"0\",\"title\":\"Home\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"2\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/6713640cfb812009acfe799920216c12/675916289/675916289.txt\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"267237378\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/cb7dbcb22cdfb1ba71cd7c0077e8bfe7/675916502/675916502.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\"has_mv\":1,\"copy_type\":\"1\",\"ting_uid\":\"239563102\",\"album_title\":\"Home\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"vip,perm-1\",\"artist_name\":\"Click#15\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_1000,h_1000\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_1000,h_1000\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675915880\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_500,h_500\",\"rank\":\"9\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"华宇世博音乐文化（北 京）有限公司-太合麦田\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"Click#15\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"110970553\",\"artist_id\":\"73365113\",\"versions\":\"\",\"publishtime\":\"2020-04-08\",\"style\":\"\",\"album_id\":\"675915878\",\"album_no\":\"1\",\"resource_type_ext\":\"2\",\"del_status\":\"0\",\"hot\":\"5596\",\"toneid\":\"0\",\"title\":\"无论黑夜多漫长\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"2\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/c2a60657ff56a87a3c4ddaf2ddf3f264/675961655/675961655.txt\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"73365113\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/ee4498322b9874df6c77a34bc0a2a6d7/675961654/675961654.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"129|-1\\\",\\\"1\\\":\\\"-1|-1\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"110970553\",\"album_title\":\"疯客感官2（The Funky Experience 2）\"},{\"country\":\"内地\",\"piao_id\":\"0\",\"res_encryption_flag\":\"0\",\"mv_provider\":\"0000000000\",\"biaoshi\":\"perm-1\",\"artist_name\":\"廖哥\",\"is_first_publish\":0,\"language\":\"国语\",\"album_1000_1000\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg\",\"korean_bb_song\":\"0\",\"pic_huge\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg\",\"all_rate\":\"64\",\"song_source\":\"web\",\"song_id\":\"675916359\",\"album_500_500\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_500,h_500\",\"rank\":\"10\",\"pic_premium\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_500,h_500\",\"album_800_800\":\"\",\"info\":\"\",\"area\":\"0\",\"si_proxycompany\":\"labelname_北京欣然文化传媒有限公司\",\"has_mv_mobile\":0,\"is_new\":\"1\",\"author\":\"廖哥\",\"resource_type\":\"0\",\"has_filmtv\":\"0\",\"all_artist_ting_uid\":\"340670120\",\"artist_id\":\"675916356\",\"versions\":\"\",\"publishtime\":\"2020-04-06\",\"style\":\"\",\"album_id\":\"675916357\",\"album_no\":\"1\",\"resource_type_ext\":\"0\",\"del_status\":\"0\",\"hot\":\"5151\",\"toneid\":\"0\",\"title\":\"惊雷\",\"pic_big\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_150,h_150\",\"relate_status\":\"0\",\"rank_change\":\"5\",\"lrclink\":\"http://qukufile2.qianqian.com/data2/lrc/48b3cdc1fdca4d73303d59e4c7e549a0/675916389/675916389.txt\",\"file_duration\":229,\"havehigh\":0,\"charge\":0,\"pic_radio\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_300,h_300\",\"learn\":0,\"pic_s500\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_500,h_500\",\"all_artist_id\":\"675916356\",\"pic_small\":\"http://qukufile2.qianqian.com/data2/pic/da2b01d86d71b23800a2645168e495a8/675916361/675916361.jpg@s_2,w_90,h_90\",\"bitrate_fee\":\"{\\\"0\\\":\\\"0|0\\\",\\\"1\\\":\\\"0|0\\\"}\",\"has_mv\":0,\"copy_type\":\"1\",\"ting_uid\":\"340670120\",\"album_title\":\"惊雷\"}]}";
        redisService.set(MusicKey.rankDetails, "", json);

    }

    @Test
    public void get(){
        String json = redisService.get(MusicKey.rankDetails, "", String.class);
        System.out.println(json);
    }


}
