package com.board.foo.dto;

import com.board.foo.domain.Board;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardForm {

    private Long id;

    @NotEmpty(message = "제목은 필수 입력 값입니다.")
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.")
    private String title;

    private String writer;

    @Size(max = 2000, message = "내용은 2000자 이하로 입력해주세요.")
    private String contents;



}
