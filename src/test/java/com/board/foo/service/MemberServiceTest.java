package com.board.foo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.board.foo.domain.Member;
import com.board.foo.repository.MemberRepository;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원가입() throws Exception{

        String userId = "아아디";
        String password = "패스워드";
        String name = "이름";

        Member member = Member.builder()
            .userId(userId)
            .password(password)
            .name(name)
            .build();

        Long saveId = memberService.join(member);

        assertThat(member.getId()).isEqualTo(saveId);
    }

    @Test
    void 중복_회원_예외() throws Exception {
        //given
        String userId = "아아디";
        String password = "패스워드";
        String name = "이름";

        Member member = Member.builder()
            .userId(userId)
            .password(password)
            .name(name)
            .build();

        String userId2 = "아아디";
        String password2 = "패스워드2";
        String name2 = "이름2";

        Member member2 = Member.builder()
            .userId(userId2)
            .password(password2)
            .name(name2)
            .build();

        //when
        memberService.join(member);

        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}