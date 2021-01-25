package com.board.foo.controller;

import com.board.foo.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class LoginController {

    private final MemberService memberService;

    //로그인 페이지
    @GetMapping("/login")
    public String loginForm(Model model) {

        log.info("로그인 Get Controller");

        // model.addAttribute("memberForm", new MemberForm());
        log.info("로그인 페이지");
        return "members/login";
    }

    //로그인 페이지
    @PostMapping("/login")
    public String login(Model model) {

        log.info("로그인 Post Controller");

        // model.addAttribute("memberForm", new MemberForm());
        log.info("로그인 완료");
        return "/";
    }

    // 추가
    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("authentication : " + authentication);

        if (authentication != null) {
            log.info("로그아웃 완료");
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

}
