package com.htw.vbbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/settings")
    public String updateUserInfo () {

        return "settings";
    }
}
