package com.htw.vbbs.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZanStoreTest {

    @Autowired
    private ZanService zanService;
    @Autowired
    private StoreService storeService;


    @Test
    public void testZan(){
        int userId = 101;
        int invitaionId = 219;
        zanService.zan(userId, invitaionId);
    }

    @Test
    public void testCancelZan(){
        int userId = 101;
        int invitaionId = 219;
        zanService.cancelZan(userId, invitaionId);
    }

    @Test
    public void testStore(){
        int userId = 101;
        int invitaionId = 219;
        storeService.store(userId, invitaionId);
    }

    @Test
    public void testCancelStore(){
        int userId = 101;
        int invitaionId = 219;
        storeService.cancelStore(userId, invitaionId);
    }

    @Test
    public void testhasStore(){
        int userId = 101;
        int invitaionId = 219;
        int as = storeService.hasStore(userId, invitaionId);
        System.out.println(as);
    }

    @Test
    public void testhasZan(){
        int userId = 101;
        int invitaionId = 210;
        int as = zanService.hasZan(userId, invitaionId);
        System.out.println(as);
    }
}
