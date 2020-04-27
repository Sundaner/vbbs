package com.htw.vbbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class InvitationController {

    @RequestMapping("/create")
    public String createArticle () {

        return "editor";
    }
}
