package com.board.foo.repository;

import com.board.foo.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
