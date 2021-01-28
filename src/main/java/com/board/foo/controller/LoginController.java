package com.board.foo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class LoginController {

    //@GetMapping("/login")만하면 안됨... 왜안되는지 모름
    @RequestMapping(value =  "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String loginForm() {

        log.info("로그인 페이지");

        return "members/login";
    }
}
