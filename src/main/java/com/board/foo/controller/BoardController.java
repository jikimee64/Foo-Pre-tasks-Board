package com.board.foo.controller;

import com.board.foo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private BoardService boardService;

    @GetMapping("boardList")
    public String boardList(){

        return "boards/boardList";
    }

}
