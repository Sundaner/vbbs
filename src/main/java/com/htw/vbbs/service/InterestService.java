package com.htw.vbbs.service;

import com.htw.vbbs.dao.InterestMapper;
import com.htw.vbbs.domain.Interest;
import com.htw.vbbs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestService {

    @Autowired
    private InterestMapper interestMapper;
    @Autowired
    private UserService userService;

    public List<User> getMyInterest(int userId) {

        List<Interest> userIds = interestMapper.getMyInterest(userId);
        List<User> users = new ArrayList<>();
        userIds.forEach(item ->{
            User user = userService.getSimpInfoById(item.getMaster());
            users.add(user);
        });
        return users;
    }

    public int interest(int userId, int master) {
        Interest interest = new Interest();
        interest.setUserId(userId);
        interest.setMaster(master);
        return interestMapper.interest(interest);
    }

    public int cancelInterest(int userId, int master) {
        Interest interest = new Interest();
        interest.setUserId(userId);
        interest.setMaster(master);
        return interestMapper.cancelInterest(interest);
    }

    public int hasInterest(int userId, int master){
        Interest interest = new Interest();
        interest.setUserId(userId);
        interest.setMaster(master);
        if(interestMapper.hasInterest(interest) != null){
            return 1;
        }
        return 0;
    }
}
