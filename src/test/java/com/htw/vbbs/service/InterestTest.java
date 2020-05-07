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
public class InterestTest {

    @Autowired
    private InterestService interestService;

    @Test
    public void testInter(){
        interestService.interest(101, 102);
    }

    @Test
    public void testCInter(){
        interestService.cancelInterest(101, 102);
    }
}
