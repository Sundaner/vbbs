package com.htw.vbbs.controller;

import com.htw.vbbs.result.Result;
import com.htw.vbbs.service.UserService;
import com.htw.vbbs.vo.RegisterVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private static Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public Result<Boolean> doRegister(HttpServletResponse response, @Valid RegisterVo registerVo) {
        log.info(registerVo.toString());
        //登录
        userService.register(response, registerVo);
        return Result.success(true);
    }
}
