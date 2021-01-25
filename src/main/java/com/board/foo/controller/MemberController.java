package com.board.foo.controller;

import com.board.foo.domain.Member;
import com.board.foo.dto.MemberDto;
import com.board.foo.dto.MemberDto.SignInReq;
import com.board.foo.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    //회원가입 페이지
    @GetMapping("/signup")
    public String signUpForm(Model model){

        log.info("회원가입 Get Controller");

        model.addAttribute("memberForm", new MemberDto.SignInReq());
        log.info("회원가입 페이지");
        return "members/signUp";
    }

    @PostMapping("/signup")
    public String signUp(@Valid MemberDto.SignInReq dto, BindingResult result){

        log.info("회원가입 Post Controller");

        if(result.hasErrors()){
            return "members/signup";
        }

        Member member = Member.builder()
            .userId(dto.getUserId())
            .password(dto.getPassword())
            .name(dto.getName())
            .build();

        memberService.join(member);

        return "redirect:/";


    }


}
