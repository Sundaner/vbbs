package com.htw.vbbs.music.response;

import com.htw.vbbs.music.model.Rank;
import lombok.Data;

import java.util.List;

@Data
public class RankResp extends BaseResp {

    private List<Rank> result;
}
