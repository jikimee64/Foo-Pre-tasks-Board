package com.board.foo.repository;

import com.board.foo.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    List<Member> findByUserId(String userId);

}
