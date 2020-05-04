package com.htw.vbbs.service;

import com.htw.vbbs.Exception.GlobalException;
import com.htw.vbbs.Result.CodeMsg;
import com.htw.vbbs.dao.UserMapper;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.redis.RedisService;
import com.htw.vbbs.redis.UserKey;
import com.htw.vbbs.util.MD5Util;
import com.htw.vbbs.util.UUIDUtil;
import com.htw.vbbs.vo.LoginVo;
import com.htw.vbbs.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        if (user != null) {
            addCookie(response, token, user);
        }
        return user;
    }

    public User getById(int id) {
        return userMapper.getById(id);
    }

    public User getByPhone(long phone) {
        return userMapper.getByPhone(phone);
    }

    public Boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String phone = loginVo.getPhone();
        String formPass = loginVo.getPassword();
        User user = getByPhone(Long.parseLong(phone));
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPass = user.getPassword();
        String slatDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, slatDB);
        if (!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.uuid();
        addCookie(response, token, user);
        return true;
    }

    public Boolean register(HttpServletResponse response, RegisterVo registerVo) {
        if (registerVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String nickname = registerVo.getNickname();
        String phone = registerVo.getPhone();
        String formPass = registerVo.getPassword();
        User user = getByPhone(Long.parseLong(phone));
        if (user != null) {
            throw new GlobalException(CodeMsg.HAVE_ACCOUNT);
        }
        String salt = UUIDUtil.uuid();
        String calcPass = MD5Util.formPassToDBPass(formPass, salt);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        User new_user = new User();
        new_user.setPhone(phone);
        new_user.setPassword(calcPass);
        new_user.setNickname(nickname);
        new_user.setSalt(salt);
        new_user.setCreateTime(timeStamp);
        new_user.setUpdateTime(timeStamp);
        userMapper.insert(new_user);
        return true;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token, token, user, UserKey.TOKEN_EXPIRE);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public User getSimpInfoById(int userId) {
        return userMapper.getSimpInfoById(userId);
    }
}
