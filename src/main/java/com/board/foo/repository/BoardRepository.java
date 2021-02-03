package com.board.foo.repository;

import com.board.foo.domain.Board;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b order by b.id desc")
    List<Board> findAll();
}
