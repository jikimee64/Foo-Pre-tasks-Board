package com.board.foo.controller;

import com.board.foo.domain.Board;
import com.board.foo.repository.BoardRepository;
import com.board.foo.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;
    private BoardRepository boardRepository;

    @GetMapping("boardList")
    public String boardList(Model model){
        List<Board> boardList = boardRepository.findAll();
        model.addAttribute("boardList", boardList);

        return "boards/boardList";
    }

}
