package com.board.foo.service;

import com.board.foo.domain.Member;
import com.board.foo.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Long join(Member member){
        validateDupicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupicateMember(Member member){
        Optional<Member> byEmail = memberRepository.findByEmail(member.getEmail());
        if(!byEmail.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * Spring Security 필수 메소드 구현
     * @param email 이메일
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속받은 Member로 반환 타입 지정 (자동으로 다운 캐스팅됨)
    public Member loadUserByUsername(String email) throws UsernameNotFoundException { // 시큐리티에서 지정한 서비스이기 때문에 이 메소드를 필수로 구현
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException((email)));
    }
}
