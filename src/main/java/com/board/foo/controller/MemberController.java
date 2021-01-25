package com.board.foo.controller;

import com.board.foo.domain.Member;
import com.board.foo.dto.MemberForm;
import com.board.foo.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    //회원가입 페이지
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
        log.info("회원가입 Post Controller");

        if(result.hasErrors()){
            log.info("에러발생");
            return "members/signup";
        }

        Member member = Member.builder()
            .email(memberForm.getEmail())
            .password(memberForm.getPassword())
            .name(memberForm.getName())
            .updatedBy(memberForm.getEmail())
            .createdBy(memberForm.getEmail())
            .build();

        memberService.join(member);
        
        log.info("회원가입 완료");

        return "redirect:/members/login";

    }

    //로그인 페이지
    @GetMapping("/login")
    public String login(Model model){

        log.info("로그인 Get Controller");

       // model.addAttribute("memberForm", new MemberForm());
        log.info("로그인 페이지");
        return "members/login";
    }



}
