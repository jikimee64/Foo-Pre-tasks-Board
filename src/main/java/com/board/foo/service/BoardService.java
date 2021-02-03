package com.board.foo.service;

import com.board.foo.domain.Board;
import com.board.foo.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findBoardList(){
        return boardRepository.findAll();
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

    public Optional<Board> boardDetail(Long id){
        return boardRepository.findById(id);
    }

}
