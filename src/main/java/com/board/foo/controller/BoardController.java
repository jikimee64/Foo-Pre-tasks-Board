package com.board.foo.controller;

import com.board.foo.domain.Board;
import com.board.foo.domain.Member;
import com.board.foo.dto.BoardForm;
import com.board.foo.repository.BoardRepository;
import com.board.foo.service.BoardService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(Model model){

        log.info("게시판 리스트 Get Controller");

        List<Board> boardList = boardService.findBoardList();
        model.addAttribute("boardList", boardList);

        return "boards/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm(Model model){
        log.info("게시판 등록폼 Get Controller");

        model.addAttribute("boardForm", new BoardForm());
        return "boards/boardForm";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model, @RequestParam("id") Long id){
        log.info("게시글 조회 Get Controller");

        model.addAttribute("board",  boardService.boardDetail(id).orElseThrow(()
            -> new NoSuchElementException()));

        return "boards/boardDetail";
    }

    @PostMapping("/boardSave")
    public String boardSave(@Valid BoardForm boardForm, BindingResult result, RedirectAttributes redirectAttributes){

        log.info("게시글 저장 Post Controller");

        if(result.hasErrors()){
            log.info("에러 발생");
            return "boards/boardForm";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();

        Board entity = Board.builder()
            .title(boardForm.getTitle())
            .writer(member.getEmail())
            .contents(boardForm.getContents())
            .build();

        Board board = boardService.saveBoard(entity);

        redirectAttributes.addAttribute("id", board.getId());
        return "redirect:/boards/boardDetail";
    }


    @GetMapping("/boardUpdate")
    public String updateForm(Model model, @RequestParam("id") Long id){
        log.info("게시글 수정 Get Controller");

        model.addAttribute("board",  boardService.boardDetail(id).orElseThrow(()
            -> new NoSuchElementException()));
        model.addAttribute("boardForm", new BoardForm());

        return "boards/boardUpdateForm";
    }

    @PreAuthorize("isAuthenticated() and (( #boardForm.getWriter() == principal.username ) or hasRole('ROLE_ADMIN'))")
    @PostMapping("/boardUpdate")
    public String update( @Valid BoardForm boardForm, RedirectAttributes redirectAttributes ){
        log.info("게시글 수정 POST Controller");

        boardService.boardUpdate(boardForm);

        redirectAttributes.addAttribute("id", boardForm.getId());

        return "redirect:/boards/boardDetail";


    }

    @PreAuthorize("isAuthenticated() and (( #email == principal.username ) or hasRole('ROLE_ADMIN'))")
    @GetMapping("/boardDelete")
    public String delete(@RequestParam("id") Long id, @RequestParam("email") String email){
        log.info("이메일 " + email);
        log.info("게시글 삭제 GET Controller");
        boardService.boardDelete(id);
        return "redirect:/boards/boardList";
    }
}
