package com.htw.vbbs.controller;

import com.htw.vbbs.config.UserArgResolver;
import com.htw.vbbs.redis.RedisService;
import com.htw.vbbs.redis.UserKey;
import com.htw.vbbs.result.Result;
import com.htw.vbbs.service.UserService;
import com.htw.vbbs.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserArgResolver userArgResolver;


    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        //登录
        userService.login(response, loginVo);
        return Result.success(true);
    }

    @RequestMapping("/logout")
    public String doLogout(HttpServletRequest request, HttpServletResponse response) {
        String token = userArgResolver.getCookieValue(request, UserService.COOKI_NAME_TOKEN);
        delLoginToken(request , response);
        redisService.del(UserKey.token, token);
        return "login";
    }

    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response){
        Cookie[] cks = request.getCookies();
        if(cks != null){
            for(Cookie ck : cks){
                if(StringUtils.equals(ck.getName(), UserService.COOKI_NAME_TOKEN)){
                    ck.setPath("/");
                    ck.setMaxAge(0);//设置成0，代表删除此cookie。
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
