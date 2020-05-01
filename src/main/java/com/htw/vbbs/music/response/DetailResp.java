package com.htw.vbbs.music.response;

import com.htw.vbbs.music.model.MusicDetail;
import com.htw.vbbs.music.model.Rank;
import lombok.Data;

import java.util.List;

@Data
public class DetailResp extends BaseResp{
    private List<MusicDetail> result;
}
