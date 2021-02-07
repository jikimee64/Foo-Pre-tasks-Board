package com.board.foo.service;

import com.board.foo.domain.Board;
import com.board.foo.dto.BoardForm;
import com.board.foo.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> findBoardList(){
        return boardRepository.findAll();
    }

    public Board saveBoard(Board board){
        return boardRepository.save(board);
    }

    public Optional<Board> boardDetail(Long id){
        return boardRepository.findById(id);
    }

    public void boardUpdate(BoardForm boardForm) {

        Optional<Board> board = boardRepository.findById(boardForm.getId());

        board.ifPresent(b -> {
            b.changeBoard(boardForm.getTitle(), boardForm.getContents());
        });
    }

    public void boardDelete(Long id){  boardRepository.deleteById(id); }


}
