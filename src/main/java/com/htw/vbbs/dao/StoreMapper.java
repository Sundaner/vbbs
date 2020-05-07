package com.htw.vbbs.dao;

import com.htw.vbbs.domain.Store;
import com.htw.vbbs.vo.InvitationVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int insertStore(Store store);

    int deleteStore(Store store);

    Store hasStore(Store store);

    List<Store> getMyStore(int userId);

    int updateTime(Store store);

    int deleteArt(int id);
}
