package com.htw.vbbs.service;

import com.htw.vbbs.exception.GlobalException;
import com.htw.vbbs.result.CodeMsg;
import com.htw.vbbs.config.UserArgResolver;
import com.htw.vbbs.dao.UserMapper;
import com.htw.vbbs.domain.User;
import com.htw.vbbs.redis.RedisService;
import com.htw.vbbs.redis.UserKey;
import com.htw.vbbs.util.MD5Util;
import com.htw.vbbs.util.UUIDUtil;
import com.htw.vbbs.vo.LoginVo;
import com.htw.vbbs.vo.RegisterVo;
import com.htw.vbbs.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserService {

    public static final String COOKI_NAME_TOKEN = "token";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserArgResolver userArgResolver;

    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        //延长有效期
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
        String def_sign = "这个人很懒，什么也没留下！";
        String def_PatUrl = "https://vbbs1-1301952348.cos.ap-beijing.myqcloud.com/upload/img/ecb1e961e23e4410819571717da848f5.png";
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
        new_user.setSign(def_sign);
        new_user.setPortrait(def_PatUrl);
        userMapper.insert(new_user);

        String token = UUIDUtil.uuid();
        addCookie(response, token, new_user);
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

    @Transactional
    public int updateImg(HttpServletRequest request, HttpServletResponse response, int userId, String url) {
        User user = new User();
        user.setUserId(userId);
        user.setPortrait(url);
        Date updateTime = new Date();
        Timestamp timeStamp = new Timestamp(updateTime.getTime());
        user.setUpdateTime(timeStamp);
        int re = userMapper.updateImg(user);
        if(re == 1){
            User new_user = userMapper.getById(userId);
            String token = userArgResolver.getCookieValue(request, COOKI_NAME_TOKEN);
            addCookie(response, token, new_user);
        }
        return re;
    }

    @Transactional
    public int updateInfo(HttpServletRequest request, HttpServletResponse response,int userId, UserVo vo) throws ParseException {
        User user = new User();
        user.setUserId(userId);
        user.setNickname(vo.getName());
        user.setPhone(vo.getPhone());
        user.setAddress(vo.getAddress());

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date=sdf.parse(vo.getBirth());

        user.setBirthday(date);
        user.setSign(vo.getSign());
        Date updateTime = new Date();
        Timestamp timeStamp = new Timestamp(updateTime.getTime());
        user.setUpdateTime(timeStamp);
        int re = userMapper.updateInfo(user);
        if(re == 1){
            User new_user = userMapper.getById(userId);
            String token = userArgResolver.getCookieValue(request, COOKI_NAME_TOKEN);
            addCookie(response, token, new_user);
        }
        return re;
    }

    public int updatePwd(HttpServletRequest request, HttpServletResponse response, int userId, String pwd2) {

        User user = new User();
        user.setUserId(userId);
        user.setPassword(pwd2);
        Date updateTime = new Date();
        Timestamp timeStamp = new Timestamp(updateTime.getTime());
        user.setUpdateTime(timeStamp);
        int re = userMapper.updatePwd(user);
        if(re == 1){
            User new_user = userMapper.getById(userId);
            String token = userArgResolver.getCookieValue(request, COOKI_NAME_TOKEN);
            addCookie(response, token, new_user);
        }
        return re;
    }
}
