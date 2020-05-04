package com.htw.vbbs.service;


import com.htw.vbbs.domain.Invitation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvitationServiceTest {

    @Autowired
    private InvitationService service;

    @Test
    public void testInsert(){
        Invitation in = new Invitation();
        in.setUserId(100);
        in.setTitle("响亮的标题");
        in.setContent("大家好");
        in.setZan(0);
        in.setType(2);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        in.setCreateTime(timeStamp);
        in.setUpdateTime(timeStamp);
        service.insertInvitation(in);
        System.out.println(in.getInvitationId());
    }

    @Test
    public void testgetById(){
        Invitation in = service.getById(202);
        System.out.println(in);
    }

    @Test
    public void testgetInviComments(){
        Invitation in = service.findInviComments(219);
        System.out.println(in);
    }
}

