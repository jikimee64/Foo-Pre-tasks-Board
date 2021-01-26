package com.board.foo.controller;

import com.board.foo.domain.Member;
import com.board.foo.dto.MemberForm;
import com.board.foo.service.MemberService;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String signUpForm(Model model){

        log.info("회원가입 Get Controller");

        model.addAttribute("memberForm", new MemberForm());
        log.info("회원가입 페이지");
        return "members/signup";
    }

    @PostMapping("/signup")
    public String signUp(
        @Valid MemberForm memberForm, BindingResult result){

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        log.info("회원가입 Post Controller");

        if(result.hasErrors()){
            log.info("에러발생");
            return "members/signup";
        }

        Member member = Member.builder()
            .email(memberForm.getEmail())
            .password(bCryptPasswordEncoder.encode(memberForm.getPassword()))
            .name(memberForm.getName())
            .auth("ROLE_USER")
            .updatedBy(memberForm.getEmail())
            .createdBy(memberForm.getEmail())
            .build();

        memberService.join(member);
        
        log.info("회원가입 완료");

        return "redirect:/members/login";
    }


    @GetMapping("/login")
    public String loginForm() {
        log.info("로그인 페이지");
        return "members/login";
    }

    @PostMapping("/login")
    public String login() {

        log.info("로그인 Post Controller");
        log.info("로그인 완료");
        return "/";
    }

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
