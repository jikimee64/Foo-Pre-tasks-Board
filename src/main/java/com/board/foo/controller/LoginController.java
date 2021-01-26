package com.board.foo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm() {

        log.info("로그인 페이지");

        return "members/login";
    }
}
